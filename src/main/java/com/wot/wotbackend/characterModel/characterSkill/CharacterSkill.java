package com.wot.wotbackend.characterModel.characterSkill;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize
public class CharacterSkill {

    private String characterSkillName;
    private String characterSkillType;
    private float characterSkillModifier;
    private int skillLevel;

    public String getCharacterSkillName() {
        return characterSkillName;
    }

    public void setCharacterSkillName(String characterSkillName) {
        this.characterSkillName = characterSkillName;
    }

    public float getCharacterSkillModifier() {
        return characterSkillModifier;
    }

    public void setCharacterSkillModifier(float characterSkillModifier) {
        this.characterSkillModifier = characterSkillModifier;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getCharacterSkillType() {
        return characterSkillType;
    }

    public void setCharacterSkillType(String characterSkillType) {
        this.characterSkillType = characterSkillType;
    }

    @Override
    public String toString(){
        return characterSkillName+" Power: "+characterSkillModifier+" Type: "+characterSkillType;
    }

}
