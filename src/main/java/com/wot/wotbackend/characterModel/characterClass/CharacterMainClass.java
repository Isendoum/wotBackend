package com.wot.wotbackend.characterModel.characterClass;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize
public class CharacterMainClass {

    private String className;
    private String ResourceName;
    private List<CharacterSkill> classSkills = new ArrayList<>();
    private int resourceBaseValue;
    private float baseAttack;
    private float baseDefence;
    private float baseMagicAttack;
    private float baseMagicDefence;
    private float baseHp;
    private float baseSpeed;




    public void addClassSkill(CharacterSkill classSkill) {
        this.classSkills.add(classSkill);
    }

}
