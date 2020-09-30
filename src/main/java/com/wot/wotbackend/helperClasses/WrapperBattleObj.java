package com.wot.wotbackend.helperClasses;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.documents.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
public class WrapperBattleObj {

    private Player player;
    private Creature creature;


}
