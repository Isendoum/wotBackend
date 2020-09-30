package com.wot.wotbackend.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.creatureModel.Creature;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Timer;

@Document
@Data
@JsonSerialize
@JsonDeserialize
@NoArgsConstructor
public class Battle {
    @Id
    private String id;
    private Player player;
    private Creature creature;
    @JsonIgnore
    private static Timer timer;




    public Battle(Player player,Creature creature){
        this.player=player;
        this.creature=creature;
    }

    public void playerAttack(){
        this.creature.setHp(this.creature.getHp()-this.player.getPlayerCharacterList().get(0).getAttack());
    }

    @Scheduled(initialDelay = 1000,fixedRate = 5000)
    public void creatureAttack(){
        int hitTaken = Math.round(((100 / this.player.getPlayerCharacterList().get(0).getDefence())/10)*this.creature.getAttack());

            this.player.getPlayerCharacterList().get(0).reduceCurrentHp(hitTaken);

        this.player.getPlayerCharacterList().get(0).reduceCurrentHp(this.creature.getAttack());
    }

}
