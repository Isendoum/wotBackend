package com.wot.wotbackend.worldStructures.town;


import com.wot.wotbackend.characterModel.characterSkill.BurstOfPower;
import com.wot.wotbackend.characterModel.characterSkill.BurstOfWill;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.characterModel.characterSkill.HealingTouch;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TownSkillShop {
    private static volatile TownSkillShop townSkillShop = new TownSkillShop();
    private List<CharacterSkill> skills= new ArrayList<>();

    private TownSkillShop(){
        skills.add(BurstOfPower.getInstance());
        skills.add(BurstOfWill.getInstance());
        skills.add(HealingTouch.getInstance());
    }

    public static TownSkillShop getInstance(){
        return townSkillShop;
    }

}
