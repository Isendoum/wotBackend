package com.wot.wotbackend.questModel;

import java.util.Random;

public enum ObjectiveType {

    KILLOBJECTIVE,
    TASKOBJECTIVE,
    TRAVELOBJECTIVE,
    ;

    public static ObjectiveType getRandomObjectiveType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
