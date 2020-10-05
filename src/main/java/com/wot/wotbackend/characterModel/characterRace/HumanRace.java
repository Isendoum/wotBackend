package com.wot.wotbackend.characterModel.characterRace;

public class HumanRace extends CharacterRace {

    private static volatile HumanRace humanRace= new HumanRace();

    private HumanRace(){
        this.setRaceName("Human");
        this.setHp(45);
        this.setAttack(40);
        this.setDefence(37);
        this.setMagicAttack(25);
        this.setMagicDefence(32);
        this.setSpeed(30);
        this.setInnerPower(100);

    }

    public static HumanRace getInstance(){

        return humanRace;
    }
}
