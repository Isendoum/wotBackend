package com.wot.wotbackend.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.wot.wotbackend.characterModel.characterSkill.BurstOfPower;
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
    private int playerAttackBuff;
    private int playerMagicAttackBuff;
    private int playerDefenceBuff;
    private int playerMagicDefenceBuff;
    private Creature creature;
    private String currentTurn;
    private String battleMessage;
    private int turn;
    private int buffedTurnsRemainingAttack;
    private int buffedTurnsRemainingMagicAttack;
    private int buffedTurnsRemainingDefence;
    private int buffedTurnsRemainingMagicDefence;





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
            this.checkRemainingBuffedTurnAndRemoveBuffs();

            switch (skillType) {

                case ATTACK:
                case ATTACKSKILL:
                    int playerAttackWithSkillModifier = Math.round((this.player.getPlayerCharacter().getAttack()+this.playerAttackBuff) * skill.getCharacterSkillModifier());
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
                    int playerMagicAttackWithSkillModifier = Math.round((this.player.getPlayerCharacter().getMagicAttack()+this.playerMagicAttackBuff) * skill.getCharacterSkillModifier());
                    int magicHitTaken = (int) (2 *Math.round(playerMagicAttackWithSkillModifier / Math.log(this.creature.getMagicDefence())));
                    if(Math.round(Math.log(this.player.getPlayerCharacter().getSpeed())) >= randomWithRange(1,10)){
                        magicHitTaken=magicHitTaken*2;
                        this.battleMessage= "You attack with "+skill.getCharacterSkillName() +" for "+magicHitTaken+" critical damage";

                    }else {
                        this.battleMessage= "You attack with "+skill.getCharacterSkillName() +" for "+magicHitTaken+" damage";
                    }
                    this.player.getPlayerCharacter().reduceCurrentInnerPower(Math.round((((float) skill.getInnerPowerConsume()/100)*this.player.getPlayerCharacter().getMaxInnerPower())));
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
                int heal = Math.round(skill.getCharacterSkillModifier() * (this.player.getPlayerCharacter().getMagicAttack()+this.playerMagicAttackBuff));
                if( (this.player.getPlayerCharacter().getCurrentHp()+heal) >= this.player.getPlayerCharacter().getMaxHp()){
                    this.player.getPlayerCharacter().setCurrentHp(this.player.getPlayerCharacter().getMaxHp());
                } else if( (this.player.getPlayerCharacter().getCurrentHp()+heal) < this.player.getPlayerCharacter().getMaxHp()){
                    this.player.getPlayerCharacter().increaseCurrentHp(heal);
                }
            this.player.getPlayerCharacter().reduceCurrentInnerPower(Math.round((((float) skill.getInnerPowerConsume()/100)*this.player.getPlayerCharacter().getMaxInnerPower())));
            battleMessage ="You healed for " + heal +" with "+skill.getCharacterSkillName();
            break;

            case "Burst of Power":
                this.buffedTurnsRemainingAttack=skill.getBuffedTurnsRemaining();
                this.buffedTurnsRemainingMagicAttack=skill.getBuffedTurnsRemaining();
                this.playerAttackBuff=Math.round((this.player.getPlayerCharacter().getAttack()*skill.getCharacterSkillModifier())/2);
                this.playerMagicAttackBuff=Math.round((this.player.getPlayerCharacter().getMagicAttack()*skill.getCharacterSkillModifier())/2);
                this.player.getPlayerCharacter().reduceCurrentInnerPower(Math.round((((float) skill.getInnerPowerConsume()/100)*this.player.getPlayerCharacter().getMaxInnerPower())));
                battleMessage ="You doubled your attack and magic attack with " +skill.getCharacterSkillName();
                break;

            case "Burst of Will":
                this.buffedTurnsRemainingDefence=skill.getBuffedTurnsRemaining();
                this.buffedTurnsRemainingMagicDefence=skill.getBuffedTurnsRemaining();
                this.playerDefenceBuff=Math.round((this.player.getPlayerCharacter().getAttack()*skill.getCharacterSkillModifier())/2);
                this.playerMagicDefenceBuff=Math.round((this.player.getPlayerCharacter().getMagicAttack()*skill.getCharacterSkillModifier())/2);
                this.player.getPlayerCharacter().reduceCurrentInnerPower(Math.round((((float) skill.getInnerPowerConsume()/100)*this.player.getPlayerCharacter().getMaxInnerPower())));
                battleMessage ="You doubled your defence and magic defence with " +skill.getCharacterSkillName();
                break;
        }

    }

    private void checkRemainingBuffedTurnAndRemoveBuffs(){
        if(this.buffedTurnsRemainingAttack>0){
            this.buffedTurnsRemainingAttack--;
        } else{
            this.playerAttackBuff=0;
        }
        System.out.println("checking m attack");
        if(this.buffedTurnsRemainingMagicAttack>0){
            this.buffedTurnsRemainingMagicAttack--;
        } else{
            this.playerMagicAttackBuff=0;
        }
        if(this.buffedTurnsRemainingDefence>0){
            this.buffedTurnsRemainingDefence--;
        } else{
            this.playerDefenceBuff=0;
        }
        if(this.buffedTurnsRemainingMagicDefence>0){
            this.buffedTurnsRemainingMagicDefence--;
        } else{
            this.playerMagicDefenceBuff=0;
        }
    }

    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
