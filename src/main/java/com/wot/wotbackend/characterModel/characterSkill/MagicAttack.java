package com.wot.wotbackend.characterModel.characterSkill;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class MagicAttack extends CharacterSkill {
    private static volatile MagicAttack magicAttack = new MagicAttack();

    private MagicAttack(){
        this.setCharacterSkillName("Magic Attack");
        this.setCharacterSkillModifier(1);
        this.setCharacterSkillType("Magic Attack");
        this.setSkillLevel(1);
        this.setSkillMaxLevel(1);
        this.setInnerPowerConsume(0);
        this.setSkillRank("D");

    }

    public static MagicAttack getInstance(){
        return magicAttack;
    }
}
