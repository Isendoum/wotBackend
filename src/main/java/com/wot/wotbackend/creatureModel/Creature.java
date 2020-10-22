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






    public Creature(String name, CreatureType creatureType, CreatureClan creatureClan,boolean isBoss) {
        this.setBoss(isBoss);
        this.setLevel(1);
        this.setCreatureType(creatureType);
        this.setCreatureClan(creatureClan);
        this.setName(name);
        this.setGold(100);
        initAll();
        this.setItems(new ArrayList<>());
        this.setCreatureSkills(new ArrayList<>());
    }
    public Creature(String name, CreatureType creatureType, CreatureClan creatureClan,boolean isBoss,int level) {
        this.setBoss(isBoss);
        this.setLevel(level);
        this.setCreatureType(creatureType);
        this.setCreatureClan(creatureClan);
        this.setName(name);
        this.setGold(Math.round(100*((float)level/10)));
        initAll();
        this.setItems(new ArrayList<>());
        this.setCreatureSkills(new ArrayList<>());
    }





}
