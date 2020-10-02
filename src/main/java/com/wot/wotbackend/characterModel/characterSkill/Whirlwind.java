package com.wot.wotbackend.characterModel.characterSkill;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;


@Data
@JsonDeserialize
public class Whirlwind extends CharacterSkill {

    private static volatile Whirlwind whirlwind= new Whirlwind();

    private Whirlwind(){
        this.setCharacterSkillName("Whirlwind");
        this.setCharacterSkillModifier(1.5f);
        this.setCharacterSkillType("Melee Attack");
        this.setSkillMaxLevel(5);
        this.setInnerPowerConsume(50);
        this.setSkillRank("C");

    }

    public static Whirlwind getInstance(){
        return whirlwind;
    }
}
