package com.wot.wotbackend.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.characterModel.characterSkill.SkillType;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.helperClasses.battleClasses.BattleType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.support.AbstractApplicationContext;
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
        SkillType skillType= skill.getCharacterSkillType();
        if((((float)skill.getInnerPowerConsume()/100)*this.player.getPlayerCharacter().getMaxInnerPower())>this.player.getPlayerCharacter().getCurrentInnerPower()){
            this.currentTurn= this.player.getPlayerCharacter().getName();
            this.battleMessage="Not enough ip";


        }else {

            switch (skillType) {

                case ATTACK:
                case ATTACKSKILL:
                    int playerAttackWithSkillModifier = Math.round(this.player.getPlayerCharacter().getAttack() * skill.getCharacterSkillModifier());
                    int hitDone = (int) (2 * Math.round(playerAttackWithSkillModifier / Math.log(this.creature.getDefence())));
                    if(Math.round(Math.log(this.player.getPlayerCharacter().getSpeed())) >= randomWithRange(1,10)){
                        hitDone=hitDone*2;
                        this.battleMessage= "You attack with "+skill.getCharacterSkillName() +" for "+hitDone+" critical damage";

                    }else {
                        this.battleMessage= "You attack with "+skill.getCharacterSkillName() +" for "+hitDone+" damage";
                    }
                    this.player.getPlayerCharacter().reduceCurrentInnerPower(Math.round((((float) skill.getInnerPowerConsume()/100)*this.player.getPlayerCharacter().getMaxInnerPower())));
                    this.creature.setHp(this.creature.getHp() - hitDone);
                    this.currentTurn = this.creature.getName();
                    this.turn++;
                    break;
                case MAGICATTACK:
                case MAGICATTACKSKILL:
                    int playerMagicAttackWithSkillModifier = Math.round(this.player.getPlayerCharacter().getMagicAttack() * skill.getCharacterSkillModifier());
                    int magicHitTaken = (int) (2 *Math.round(playerMagicAttackWithSkillModifier / Math.log(this.creature.getMagicDefence())));
                    if(Math.round(Math.log(this.player.getPlayerCharacter().getSpeed())) >= randomWithRange(1,10)){
                        magicHitTaken=magicHitTaken*2;
                        this.battleMessage= "You attack with "+skill.getCharacterSkillName() +" for "+magicHitTaken+" critical damage";

                    }else {
                        this.battleMessage= "You attack with "+skill.getCharacterSkillName() +" for "+magicHitTaken+" damage";
                    }
                    this.player.getPlayerCharacter().reduceCurrentInnerPower(skill.getInnerPowerConsume());
                    this.creature.setHp(this.creature.getHp() - magicHitTaken);
                    this.currentTurn = this.creature.getName();
                    this.turn++;
                    break;
                case BATTLEBUFF:
                    battleBuffSkillChecker(skill);
                    this.currentTurn = this.creature.getName();
                    this.turn++;
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

    private void battleBuffSkillChecker(CharacterSkill skill){

        switch (skill.getCharacterSkillName()){
        case "Healing Touch":
            int heal = Math.round(skill.getCharacterSkillModifier() * this.player.getPlayerCharacter().getMagicAttack());
            if( (this.player.getPlayerCharacter().getCurrentHp()+heal) >= this.player.getPlayerCharacter().getMaxHp()){
                this.player.getPlayerCharacter().setCurrentHp(this.player.getPlayerCharacter().getMaxHp());
            } else if( (this.player.getPlayerCharacter().getCurrentHp()+heal) < this.player.getPlayerCharacter().getMaxHp()){
            this.player.getPlayerCharacter().increaseCurrentHp(heal);
            }
            this.player.getPlayerCharacter().reduceCurrentInnerPower(skill.getInnerPowerConsume());
            battleMessage ="You healed for " + heal +" with "+skill.getCharacterSkillName();

            break;
            case "Another Skill":
                break;
        }

    }

    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
