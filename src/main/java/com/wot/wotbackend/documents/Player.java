package com.wot.wotbackend.documents;




import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.characterModel.Character;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
public class Player {


    @Id
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Date lastDate;
    private List<Character> playerCharacterList= new ArrayList<>();


    public Player(String playerName,Character character){
        this.name=playerName;
        this.lastDate=new Date();
        this.playerCharacterList.add(character);
    }

}
