package com.wot.wotbackend.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
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
    private String currentTurn;
    private int turn;
    @JsonIgnore
    private static Timer timer;





    public Battle(Player player,Creature creature){
        this.player=player;
        this.creature=creature;
        this.turn=1;
        if(player.getPlayerCharacterList().get(0).getSpeed()>=creature.getSpeed()){
            this.currentTurn=this.player.getName();
        }else{
            this.currentTurn=this.creature.getName();
        }
    }


    public void playerAttackMove(CharacterSkill skill){
        String skillType= skill.getCharacterSkillType();
        switch (skillType)
        {
            case "Melee Attack":
                int playerAttackWithSkillModifier= Math.round(this.player.getPlayerCharacterList().get(0).getAttack()*skill.getCharacterSkillModifier());
                int hitTaken = Math.round(playerAttackWithSkillModifier-(this.creature.getDefence()*0.1f));
                System.out.println("Hit damaged for: " +hitTaken);
                this.player.getPlayerCharacterList().get(0).reduceCurrentInnerPower(skill.getInnerPowerConsume());
                this.creature.setHp(this.creature.getHp()-hitTaken);
                this.currentTurn=this.creature.getName();
                this.turn++;
            break;
            case "Magic Attack":
                int playerMagicAttackWithSkillModifier = Math.round(this.player.getPlayerCharacterList().get(0).getMagicAttack()*skill.getCharacterSkillModifier());
                int magicHitTaken = Math.round(playerMagicAttackWithSkillModifier-(this.creature.getMagicDefence()*0.1f));
                System.out.println("Magic Hit damaged for: " +magicHitTaken);
                this.player.getPlayerCharacterList().get(0).reduceCurrentInnerPower(skill.getInnerPowerConsume());
                this.creature.setHp(this.creature.getHp()-magicHitTaken);
                this.currentTurn=this.creature.getName();
                this.turn++;
                break;
        }

    }


    public void creatureAttack(){
        int hitTaken = Math.round(this.creature.getAttack()-(this.player.getPlayerCharacterList().get(0).getDefence()*0.1f));
        this.player.getPlayerCharacterList().get(0).reduceCurrentHp(hitTaken);
        this.currentTurn=this.player.getName();
        this.turn++;
    }

}
