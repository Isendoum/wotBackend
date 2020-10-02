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
    private int skillLevel =1;
    private  int skillMaxLevel;
    private int innerPowerConsume;
    private String skillRank;



    public void setCharacterSkillModifier(float characterSkillModifier) {
        this.characterSkillModifier = characterSkillModifier;
    }

    @Override
    public String toString(){
        return characterSkillName+" Power: "+characterSkillModifier+" Type: "+characterSkillType;
    }

}
