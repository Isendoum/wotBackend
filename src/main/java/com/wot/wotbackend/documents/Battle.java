package com.wot.wotbackend.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.helperClasses.battleClasses.BattleType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Timer;

@Document
@Data
@JsonSerialize
@JsonDeserialize
@NoArgsConstructor
public class Battle {
    @Id
    private String id;
    private BattleType battleTypeEnum;
    private Player player;
    private Creature creature;
    private String currentTurn;
    private String battleMessage;
    private int turn;
    @JsonIgnore
    private static Timer timer;





    public Battle(Player player,Creature creature,BattleType battleType){
        this.setBattleTypeEnum(battleType);
        this.player=player;
        this.creature=creature;
        this.turn=1;
        if(player.getPlayerCharacter().getSpeed()>=creature.getSpeed()){
            this.currentTurn=this.player.getUsername();

        }else{
            this.currentTurn=this.creature.getName();
        }
        this.battleMessage= this.currentTurn+" plays first";

    }


    public void playerAttackMove(CharacterSkill skill){
        String skillType= skill.getCharacterSkillType();
        if((((float)skill.getInnerPowerConsume()/100)*this.player.getPlayerCharacter().getMaxInnerPower())>this.player.getPlayerCharacter().getCurrentInnerPower()){
            this.currentTurn= this.player.getPlayerCharacter().getName();
            this.battleMessage="Not enough ip";


        }else {
            switch (skillType) {
                case "Melee Attack":
                    int playerAttackWithSkillModifier = Math.round(this.player.getPlayerCharacter().getAttack() * skill.getCharacterSkillModifier());
                    int hitTaken = (int) (2 * Math.round(playerAttackWithSkillModifier / Math.log(this.creature.getDefence())));
                    System.out.println("Hit damaged for: " + hitTaken);
                    this.player.getPlayerCharacter().reduceCurrentInnerPower(Math.round((((float) skill.getInnerPowerConsume()/100)*this.player.getPlayerCharacter().getMaxInnerPower())));
                    this.creature.setHp(this.creature.getHp() - hitTaken);
                    this.currentTurn = this.creature.getName();
                    this.turn++;
                    this.battleMessage= "You attack with "+skill.getCharacterSkillName() +" for "+hitTaken+" damage";
                    break;
                case "Magic Attack":
                    int playerMagicAttackWithSkillModifier = Math.round(this.player.getPlayerCharacter().getMagicAttack() * skill.getCharacterSkillModifier());
                    int magicHitTaken = (int) (2 *Math.round(playerMagicAttackWithSkillModifier / Math.log(this.creature.getMagicDefence())));
                    System.out.println("Magic Hit damaged for: " + magicHitTaken);
                    this.player.getPlayerCharacter().reduceCurrentInnerPower(skill.getInnerPowerConsume());
                    this.creature.setHp(this.creature.getHp() - magicHitTaken);
                    this.currentTurn = this.creature.getName();
                    this.turn++;
                    this.battleMessage= "You attack with "+skill.getCharacterSkillName() +" for "+magicHitTaken+" damage";
                    break;
            }
        }

    }


    public void creatureAttack(){
        int hitTaken =((int) Math.round(this.creature.getAttack()/Math.log(this.player.getPlayerCharacter().getDefence())));
        this.player.getPlayerCharacter().reduceCurrentHp(hitTaken);
        this.currentTurn=this.player.getUsername();
        this.turn++;
        this.battleMessage= this.creature.getName()+" attacked you "+" for "+hitTaken;
    }

}
