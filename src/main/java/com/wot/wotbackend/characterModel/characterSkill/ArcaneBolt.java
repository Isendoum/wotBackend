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
        this.setCharacterSkillType(SkillType.MAGICATTACKSKILL);
        this.setSkillLevel(1);
        this.setSkillMaxLevel(5);
        this.setInnerPowerConsume(10);
        this.setSkillRank("C");
        this.setSkillDescription("Throw an arcane bolt causing " +this.getCharacterSkillModifier()*100+"% magic damage to the target. Consumes "+this.getInnerPowerConsume()+"% of inner power.");

    }

    public static ArcaneBolt getInstance(){
        return magicAttack;
    }
}
