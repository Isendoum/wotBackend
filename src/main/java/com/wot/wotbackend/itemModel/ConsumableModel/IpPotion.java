package com.wot.wotbackend.itemModel.ConsumableModel;


import com.wot.wotbackend.itemModel.Abilities.HealHp;
import com.wot.wotbackend.itemModel.Abilities.HealIp;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.itemModel.ItemType;
import lombok.Data;


@Data
public class IpPotion extends Item {

    public IpPotion(){
        this.setQuantity(1);
        this.setItemName("Ip Potion");
        this.setItemAbility(HealIp.getInstance());
        this.setItemType(ItemType.CONSUMABLE);
        this.setGoldValue(200);

    }




}
