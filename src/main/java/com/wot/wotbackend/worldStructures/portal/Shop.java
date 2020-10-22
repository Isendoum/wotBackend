package com.wot.wotbackend.worldStructures.portal;

import com.wot.wotbackend.itemModel.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Shop extends StructureModel {
    private List<Item> shopList;


    public Shop(){
        this.setStructureType("Shop");
        this.shopList= new ArrayList<>();

    }

    public void addItemsToShop(Item item){
            shopList.add(item);
    }
}
