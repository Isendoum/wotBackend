package com.wot.wotbackend.characterModel.characterRace;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize
public class CharacterRace {

    private String raceName;
    private List<CharacterSkill> raceSkills;
    private float statModifier;

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public List<CharacterSkill> getClassSkills() {
        return raceSkills;
    }

    public void addClassSkill(CharacterSkill raceSkill) {
        this.raceSkills.add(raceSkill);
    }

    public float getStatModifier() {
        return statModifier;
    }

    public void setStatModifier(float statModifier) {
        this.statModifier = statModifier;
    }
}
