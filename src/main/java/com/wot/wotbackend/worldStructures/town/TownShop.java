package com.wot.wotbackend.worldStructures.town;

import com.wot.wotbackend.characterModel.characterSkill.ArcaneBolt;
import com.wot.wotbackend.itemModel.ConsumableModel.IpPotion;
import com.wot.wotbackend.itemModel.ConsumableModel.Potion;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.itemModel.Weapons.SolomansResolve;
import com.wot.wotbackend.worldStructures.portal.Shop;
import lombok.Data;

import java.util.List;

@Data
public class TownShop extends Shop {

    private static volatile TownShop townShop = new TownShop();

    private TownShop(){
        addItemsToShop(new Potion());
        addItemsToShop(new IpPotion());
        addItemsToShop(new SolomansResolve());
    }

    public static TownShop getInstance(){
        return townShop;
    }
}
