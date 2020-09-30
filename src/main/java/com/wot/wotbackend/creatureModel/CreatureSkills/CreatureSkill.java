package com.wot.wotbackend.creatureModel.CreatureSkills;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonDeserialize
@NoArgsConstructor

public class CreatureSkill {

    String skillName;
    float skillModifier;

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public float getSkillModifier() {
        return skillModifier;
    }

    public void setSkillModifier(float skillModifier) {
        this.skillModifier = skillModifier;
    }
}
