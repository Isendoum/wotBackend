package com.wot.wotbackend.characterModel.characterRace;

public class HumanRace extends CharacterRace {

    private static volatile HumanRace humanRace= new HumanRace();

    private HumanRace(){
        this.setRaceName("Human");
        this.setHp(85);
        this.setAttack(25);
        this.setDefence(30);
        this.setMagicAttack(25);
        this.setMagicDefence(30);
        this.setSpeed(30);
        this.setInnerPower(50);
    }

    public static HumanRace getInstance(){
        return humanRace;
    }
}
