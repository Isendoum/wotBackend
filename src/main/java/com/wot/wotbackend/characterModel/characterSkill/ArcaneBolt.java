package com.wot.wotbackend.characterModel.characterSkill;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class ArcaneBolt extends CharacterSkill {
    private static volatile ArcaneBolt magicAttack = new ArcaneBolt();

    private ArcaneBolt(){
        this.setCharacterSkillName("Arcane Bolt");
        this.setCharacterSkillModifier(1.5f);
        this.setCharacterSkillType("Magic Attack");
        this.setSkillLevel(1);
        this.setSkillMaxLevel(5);
        this.setInnerPowerConsume(50);
        this.setSkillRank("C");

    }

    public static ArcaneBolt getInstance(){
        return magicAttack;
    }
}
