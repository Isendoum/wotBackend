package com.wot.wotbackend.creatureModel.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.creatureModel.CreatureSkills.CreatureSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@JsonDeserialize
@NoArgsConstructor

public class CreatureType {

    private float hpModifier;
    private float meleeModifier;
    private float magicModifier;
    private int exp;
    private String typeName;
    private List<CreatureSkill> creatureSkills;




    public List<CreatureSkill> getCreatureSkills() {
        return creatureSkills;
    }

    public void setCreatureSkills(List<CreatureSkill> creatureSkills) {
        this.creatureSkills = creatureSkills;
    }
}
