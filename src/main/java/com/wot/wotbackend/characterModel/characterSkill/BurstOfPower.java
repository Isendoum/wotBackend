package com.wot.wotbackend.characterModel.characterSkill;


import lombok.Data;

@Data
public class BurstOfPower extends CharacterSkill {

    private static volatile BurstOfPower burstOfPower = new BurstOfPower();
    private BurstOfPower(){
        this.setBuffedTurnsRemaining(3);
        this.setCharacterSkillName("Burst of Power");
        this.setCrystalCost(1);
        this.setCharacterSkillModifier(2f);
        this.setCharacterSkillType(SkillType.BATTLEBUFF);
        this.setSkillLevel(1);
        this.setSkillMaxLevel(5);
        this.setInnerPowerConsume(20);
        this.setSkillRank("B");
        this.setSkillDescription("Increases attack and magic attack for 3 rounds by " +this.getCharacterSkillModifier()*100+"% . Consumes "+this.getInnerPowerConsume()+"% of inner power.");

    }

    public static BurstOfPower getInstance(){
        return burstOfPower;
    }
}
