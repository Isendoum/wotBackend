package com.wot.wotbackend.creatureModel.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class Humanoid extends CreatureType {

    private static volatile Humanoid humanoid = new Humanoid();

    public Humanoid() {
        this.setExp(50);
        this.setHpModifier(20);
        this.setTypeName("Humanoid");
        this.setMeleeModifier(5);
        this.setMagicModifier(2);
    }

    public static Humanoid getInstance(){
        return humanoid;
    }


}
