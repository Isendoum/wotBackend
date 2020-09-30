package com.wot.wotbackend.characterModel.characterRace;

public class HumanRace extends CharacterRace {

    private static volatile HumanRace humanRace= new HumanRace();

    private HumanRace(){
        this.setRaceName("Human");
        this.setStatModifier(1);

    }

    public static HumanRace getInstance(){

        return humanRace;
    }
}
