package com.wot.wotbackend.config;

import java.io.IOException;

import com.wot.wotbackend.helperClasses.payloads.chatPayloads.ChatPayload;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        System.out.println(jsonObject);
        session.sendMessage(new TextMessage(jsonObject.getString("message")));
    }

}
