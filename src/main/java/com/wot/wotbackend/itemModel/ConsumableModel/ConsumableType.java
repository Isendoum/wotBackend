package com.wot.wotbackend.itemModel.ConsumableModel;

import com.wot.wotbackend.itemModel.ItemType;

import java.util.Random;

public enum ConsumableType {
    POTION,
    IPPOTION;

    public static ConsumableType getRandomConsumableType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
