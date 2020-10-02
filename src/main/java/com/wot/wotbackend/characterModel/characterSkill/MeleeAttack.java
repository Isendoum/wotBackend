package com.wot.wotbackend.characterModel.characterSkill;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class MeleeAttack extends CharacterSkill {
    private static volatile MeleeAttack meleeAttack = new MeleeAttack();

    private MeleeAttack(){
        this.setCharacterSkillName("Attack");
        this.setCharacterSkillModifier(1);
        this.setCharacterSkillType("Melee Attack");
        this.setSkillLevel(1);
        this.setSkillMaxLevel(1);
        this.setInnerPowerConsume(0);
        this.setSkillRank("D");

    }

    public static MeleeAttack getInstance(){
        return meleeAttack;
    }
}
