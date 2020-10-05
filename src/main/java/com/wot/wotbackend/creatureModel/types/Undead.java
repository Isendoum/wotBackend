package com.wot.wotbackend.creatureModel.types;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class Undead extends CreatureType {

    private static volatile Undead undead = new Undead();

    private Undead(){
        this.setExp(110);
        this.setHpModifier(35);
        this.setTypeName("Undead");
        this.setMeleeModifier(10);
        this.setMagicModifier(10);
    }

    public static Undead getInstance(){
        return undead;
    }

}
