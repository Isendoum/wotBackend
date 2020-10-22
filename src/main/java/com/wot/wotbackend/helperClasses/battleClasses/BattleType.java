package com.wot.wotbackend.helperClasses.battleClasses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public enum BattleType {
    WORLD,
    VALORTOWER,
}
