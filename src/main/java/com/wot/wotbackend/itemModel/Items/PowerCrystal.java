package com.wot.wotbackend.itemModel.Items;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.itemModel.ItemType;
import lombok.Data;

@Data
@JsonSerialize
@JsonDeserialize
public class PowerCrystal extends Item {

    public PowerCrystal(){
        this.setItemName("Power Crystal");
        this.setItemType(ItemType.POWERCRYSTAL);
    }

}
