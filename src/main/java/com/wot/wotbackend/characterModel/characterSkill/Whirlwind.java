package com.wot.wotbackend.characterModel.characterSkill;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;


@Data
@JsonDeserialize
public class Whirlwind extends CharacterSkill {

    private static volatile Whirlwind whirlwind= new Whirlwind();

    private Whirlwind(){
        this.setCharacterSkillName("Whirlwind");
        this.setCharacterSkillModifier(1);
        this.setSkillLevel(1);
        this.setCharacterSkillType("Attack");

    }

    public static Whirlwind getInstance(){
        return whirlwind;
    }
}
