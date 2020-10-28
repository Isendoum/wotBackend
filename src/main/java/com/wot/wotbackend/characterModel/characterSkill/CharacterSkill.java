package com.wot.wotbackend.characterModel.characterSkill;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.documents.Battle;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize
public class CharacterSkill {

    private String characterSkillName;
    private SkillType characterSkillType;
    private String skillDescription;
    private float characterSkillModifier;
    private int skillLevel =1;
    private  int skillMaxLevel;
    private int innerPowerConsume;
    private String skillRank;





    @Override
    public String toString(){
        return characterSkillName+" Power: "+characterSkillModifier+" Type: "+characterSkillType;
    }

    public Battle returnBattleWithBuffs(Battle battle){

        return battle;
    }

}
