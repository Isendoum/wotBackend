package com.wot.wotbackend.config;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

    public static List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        if(jsonObject.get("message").toString().equals("disconnect")){
            System.out.println("someone disconnected");
            for (int i = 0; i < sessions.size(); i++) {
                if(sessions.get(i).getId().equals(session.getId())){
                    sessions.remove(i);
                    session.close();
                }
            }
        }else{
            System.out.println(jsonObject.getString("user"));
            if(sessions.size()==0){
                sessions.add(session);
                System.out.println("first connection");
                session.sendMessage(new TextMessage(jsonObject.getString("user")+" "+jsonObject.get("message").toString()));
            }else{
                System.out.println("Sub-sequence connection");
                if (doesSessionExist(session)){

                        sendMessageToAll(jsonObject.getString("user")+":"+jsonObject.get("message").toString());

                }else
                {
                    sessions.add(session);

                    sendMessageToAll(jsonObject.getString("user")+":"+jsonObject.get("message").toString());

                }
            }
        }
    }

    private boolean doesSessionExist(WebSocketSession session){

        for (WebSocketSession session1:sessions){
            if (session1.getId().equals(session.getId())){
                return true;
            }
        }
        return false;
    }

    private void sendMessageToAll(String message) throws IOException {
        for (int i = 0; i < sessions.size() ; i++) {
            sessions.get(i).sendMessage(new TextMessage(message));
        }
    }

}
