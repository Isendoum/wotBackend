package com.wot.wotbackend.creatureModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.creatureModel.creatureClan.CreatureClan;
import com.wot.wotbackend.creatureModel.types.CreatureType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@JsonSerialize
@JsonDeserialize
public class Creature extends CreatureModel {






    public Creature(String name, CreatureType creatureType, CreatureClan creatureClan) {
        this.setLevel(1);
        this.setCreatureType(creatureType);
        this.setCreatureClan(creatureClan);
        this.setName(name);
        this.setGold(300);
        initAll();
        this.setItems(new ArrayList<>());
        this.setCreatureSkills(new ArrayList<>());
    }





}
