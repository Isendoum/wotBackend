package com.wot.wotbackend.itemModel.Weapons;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.itemModel.Item;
import lombok.Data;

@Data
@JsonSerialize
@JsonDeserialize

public class SolomansResolve extends Item {

    public SolomansResolve(){
        this.setLevelRequired(20);
        this.setGoldValue(30000);
        this.setAttackModifier(100);
        this.setMagicAttackModifier(120);
        this.setHpModifier(150);
        this.setMagicAttackModifier(120);
        this.setMagicDefenceModifier(60);
        this.setDefenceModifier(50);
        this.setItemType("Weapon");
        this.setItemName("Soloman's Resolve");
    }

}
