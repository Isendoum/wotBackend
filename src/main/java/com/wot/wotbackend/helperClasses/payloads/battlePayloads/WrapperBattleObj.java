package com.wot.wotbackend.helperClasses.payloads.battlePayloads;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.documents.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
@JsonSerialize
public class WrapperBattleObj {

    private Player player;
    private Creature creature;


}
