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
        this.setCharacterSkillType(SkillType.ATTACKSKILL);
        this.setSkillMaxLevel(5);
        this.setInnerPowerConsume(10);
        this.setSkillRank("C");
        this.setSkillDescription("Swing you weapon wildly, causing " +this.getCharacterSkillModifier()*100+"% melee damage to the target. Consumes "+this.getInnerPowerConsume()+"% of inner power.");

    }

    public static WildSwing getInstance(){
        return wildSwing;
    }
}
