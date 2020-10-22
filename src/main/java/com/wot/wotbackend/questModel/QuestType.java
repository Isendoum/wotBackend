package com.wot.wotbackend.questModel;

import com.wot.wotbackend.itemModel.ItemType;

import java.util.Random;

public enum QuestType {
    MAINQUEST,
    DAILYQUEST,
    EVENTQUEST,
    ;

    public static QuestType getRandomQuestType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
