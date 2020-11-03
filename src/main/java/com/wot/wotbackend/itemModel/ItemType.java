package com.wot.wotbackend.itemModel;

import java.util.Random;

public enum ItemType {

    WEAPON,
    OFFHAND,
    HELMET,
    CHEST,
    GLOVES,
    SHOULDERS,
    PANTS,
    BOOTS,
    RING,
    AMULET,
    CONSUMABLE,
    POWERCRYSTAL;

    public static ItemType getRandomItemType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}

