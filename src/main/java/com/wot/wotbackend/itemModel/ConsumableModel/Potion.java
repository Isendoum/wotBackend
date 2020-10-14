package com.wot.wotbackend.itemModel.ConsumableModel;



import com.wot.wotbackend.itemModel.Abilities.HealHp;

import com.wot.wotbackend.itemModel.Item;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class Potion extends Item {

    public Potion(){
        this.setQuantity(1);
        this.setItemName("Potion");
        this.setItemAbility(HealHp.getInstance());
        this.setItemType("Consumable");
        this.setGoldValue(200);

    }




}
