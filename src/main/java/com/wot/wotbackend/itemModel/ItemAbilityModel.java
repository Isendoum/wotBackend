package com.wot.wotbackend.itemModel;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
public abstract class ItemAbilityModel {

    private String abilityDescription;
    private float abilityModifier;


    @Override
    public String toString(){
        return "Ability : "+abilityDescription;
    }
}
