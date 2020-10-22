package com.wot.wotbackend.questModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.creatureModel.Creature;


@JsonDeserialize
@JsonSerialize
public interface IObjective {
    public void areTasksDone();
    public void checkObjective(String action);
    public void checkObjective(Creature creature);
    public void checkObjective();

}
