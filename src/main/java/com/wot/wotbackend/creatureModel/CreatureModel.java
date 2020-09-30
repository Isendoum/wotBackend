package com.wot.wotbackend.creatureModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.creatureModel.CreatureSkills.CreatureSkill;
import com.wot.wotbackend.creatureModel.creatureClan.CreatureClan;
import com.wot.wotbackend.creatureModel.types.CreatureType;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonDeserialize
public abstract class CreatureModel {


    private String id = UUID.randomUUID().toString();
    private double latitude;
    private double longitude;
    private float hp;
    private float defence;
    private float attack;
    private float magicAttack;
    private float magicDefence;
    private float speed;
    private int level;
    private int exp;
    private String name;
    private CreatureClan creatureClan;
    private CreatureType creatureType;
    private List<Item> items;
    private List<CreatureSkill> creatureSkills;



    public void initExp(){
        this.exp=(this.creatureClan.getExp()+this.creatureType.getExp())*this.level;
    }

    public void initHp(){
        this.hp=(creatureClan.getHpModifier()+creatureType.getHpModifier())*this.level;
    }

    public void initDefence(){
        this.defence=(creatureClan.getMeleeModifier()+creatureType.getMeleeModifier())*this.level;
    }
    public void initAttack(){
        this.attack=(creatureClan.getMeleeModifier()+creatureType.getMeleeModifier())*this.level;
    }

    public void initMagicAttack(){
        this.magicAttack=(creatureClan.getMagicModifier()+creatureType.getMagicModifier())*this.level;
    }

    public void initMagicDefence(){
        this.magicDefence=(creatureClan.getMagicModifier()+creatureType.getMagicModifier())*this.level;
    }

    public void initName(){
        this.name= this.creatureType.getTypeName()+" "+ this.name + " of "+this.creatureClan.getClanName();
    }

    //initializes all the init methods
    public void initAll(){

        initHp();
        initAttack();
        initDefence();
        initMagicAttack();
        initMagicDefence();
        initExp();
        initName();
    }
    //runs all the stat initializer methods to recalculate the stats and exp of the creature
    public void recalculateStats(){
        initHp();
        initAttack();
        initDefence();
        initMagicAttack();
        initMagicDefence();
        initExp();
    }
}
