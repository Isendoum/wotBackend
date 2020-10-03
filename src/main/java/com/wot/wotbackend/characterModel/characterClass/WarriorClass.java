package com.wot.wotbackend.characterModel.characterClass;

import com.wot.wotbackend.characterModel.characterSkill.WildSwing;

public class WarriorClass extends CharacterMainClass {

    private static volatile WarriorClass warriorClass= new WarriorClass();



    private WarriorClass(){
        this.setResourceName("Rage");
        this.setClassName("Warrior");
        this.setResourceBaseValue(0);
        this.setBaseHp(50);
        this.setBaseAttack(6);
        this.setBaseDefence(9);
        this.setBaseMagicAttack(1);
        this.setBaseMagicDefence(3);
        this.setBaseSpeed(2);
        this.addClassSkill(WildSwing.getInstance());


    }

    public static WarriorClass getInstance(){
        return warriorClass;
    }


}
