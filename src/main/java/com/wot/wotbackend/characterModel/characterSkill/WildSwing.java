package com.wot.wotbackend.characterModel.characterSkill;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;


@Data
@JsonDeserialize
public class WildSwing extends CharacterSkill {

    private static volatile WildSwing wildSwing = new WildSwing();

    private WildSwing(){
        this.setCharacterSkillName("Wild Swing");
        this.setCharacterSkillModifier(1.5f);
        this.setCharacterSkillType("Melee Attack");
        this.setSkillMaxLevel(5);
        this.setInnerPowerConsume(10);
        this.setSkillRank("C");

    }

    public static WildSwing getInstance(){
        return wildSwing;
    }
}
