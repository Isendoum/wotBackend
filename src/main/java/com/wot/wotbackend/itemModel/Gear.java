package com.wot.wotbackend.itemModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.itemModel.GearModels.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonDeserialize
public class Gear {

    private Item weapon;
    private Item offHand;
    private Item helmet;
    private Item chest;
    private Item gloves;
    private Item pants;
    private Item shoulders;
    private Item boots;
    private Item amulet;
    private Item ring1;
    private Item ring2;

    public Gear(){
        this.weapon = new Weapon();
        this.offHand = new OffHand();
        this.helmet = new Helmet();
        this.chest = new Chest();
        this.gloves = new Gloves();
        this.pants = new Pants();
        this.shoulders = new Shoulders();
        this.boots = new Boots();
        this.amulet = new Amulet();
        this.ring1 = new Ring();
        this.ring2 = new Ring();
    }
}
