package com.wot.wotbackend.characterModel.characterSkill;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonDeserialize
@JsonSerialize
public class HealingTouch extends CharacterSkill {
    private static volatile HealingTouch magicAttack = new HealingTouch();

    private HealingTouch(){
        this.setCharacterSkillName("Healing Touch");
        this.setCharacterSkillModifier(0.1f);
        this.setCharacterSkillType(SkillType.BATTLEBUFF);
        this.setSkillLevel(1);
        this.setSkillMaxLevel(5);
        this.setInnerPowerConsume(15);
        this.setSkillRank("C");
        this.setSkillDescription("Heal for " +this.getCharacterSkillModifier()*100+"% of your magic damage. Consumes "+this.getInnerPowerConsume()+"% of inner power to use.");

    }

    public static HealingTouch getInstance(){
        return magicAttack;
    }
}
