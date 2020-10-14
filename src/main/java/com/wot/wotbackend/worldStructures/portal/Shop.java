package com.wot.wotbackend.worldStructures.portal;

import com.wot.wotbackend.itemModel.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Shop extends StructureModel {
    private List<Item> shopList;
    private int level;

    public Shop(int level){
        this.setStructureType("Shop");
        this.level= level;
        this.shopList= new ArrayList<>();

    }

    public void addItemsToShop(){
        for (int i = 1; i < 10 ; i++) {
            shopList.add(Item.createRandomItem(this.level));
        }
    }
}
