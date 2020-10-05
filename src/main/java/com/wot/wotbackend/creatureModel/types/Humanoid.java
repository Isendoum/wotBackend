package com.wot.wotbackend.creatureModel.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class Humanoid extends CreatureType {

    private static volatile Humanoid humanoid = new Humanoid();

    public Humanoid() {
        this.setExp(90);
        this.setHpModifier(15);
        this.setTypeName("Humanoid");
        this.setMeleeModifier(8);
        this.setMagicModifier(6);
    }

    public static Humanoid getInstance(){
        return humanoid;
    }


}
