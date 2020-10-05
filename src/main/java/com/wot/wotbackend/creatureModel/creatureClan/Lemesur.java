package com.wot.wotbackend.creatureModel.creatureClan;


public class Lemesur extends CreatureClan {


    private static volatile Lemesur lemesur = new Lemesur();

    private Lemesur(){

        this.setExp(10);
        this.setClanName("Lemesur");
        this.setHpModifier(30);
        this.setMeleeModifier(5);
        this.setMagicModifier(2);


    }

    public static Lemesur getInstance(){
        return lemesur;
    }
}
