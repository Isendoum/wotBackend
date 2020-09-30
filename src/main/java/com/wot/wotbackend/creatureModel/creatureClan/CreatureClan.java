package com.wot.wotbackend.creatureModel.creatureClan;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.creatureModel.CreatureSkills.CreatureSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonDeserialize
@NoArgsConstructor
@AllArgsConstructor
public class CreatureClan {

    private float hpModifier;
    private float meleeModifier;
    private float magicModifier;
    private int exp;
    private String clanName;
    private CreatureSkill[] creatureSkills;



    public CreatureSkill[] getCreatureSkills() {
        return creatureSkills;
    }

    public void setCreatureSkills(CreatureSkill[] creatureSkills) {
        this.creatureSkills = creatureSkills;
    }
}
