package com.wot.wotbackend.creatureModel.types;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class Undead extends CreatureType {

    private static volatile Undead undead = new Undead();

    private Undead(){
        this.setExp(70);
        this.setHpModifier(25);
        this.setTypeName("Undead");
        this.setMeleeModifier(4);
        this.setMagicModifier(4);
    }

    public static Undead getInstance(){
        return undead;
    }

}
