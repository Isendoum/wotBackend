package com.wot.wotbackend.characterModel.characterSkill;


import lombok.Data;

@Data
public class BurstOfWill extends CharacterSkill {

    private static volatile BurstOfWill burstOfWill = new BurstOfWill();
    private BurstOfWill(){
        this.setBuffedTurnsRemaining(3);
        this.setCharacterSkillName("Burst of Will");
        this.setCrystalCost(1);
        this.setCharacterSkillModifier(2f);
        this.setCharacterSkillType(SkillType.BATTLEBUFF);
        this.setSkillLevel(1);
        this.setSkillMaxLevel(5);
        this.setInnerPowerConsume(20);
        this.setSkillRank("B");
        this.setSkillDescription("Increases defence and magic defence for 3 rounds by " +this.getCharacterSkillModifier()*100+"% . Consumes "+this.getInnerPowerConsume()+"% of inner power.");

    }

    public static BurstOfWill getInstance(){
        return burstOfWill;
    }
}
