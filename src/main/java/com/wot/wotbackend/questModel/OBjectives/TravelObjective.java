package com.wot.wotbackend.questModel.OBjectives;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.questModel.IObjective;
import com.wot.wotbackend.questModel.Objective;
import com.wot.wotbackend.questModel.ObjectiveType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize
@JsonSerialize

public class TravelObjective extends Objective implements IObjective {



    public TravelObjective(int maxTasks){
        this.setMaxTasks(maxTasks);
        this.setObjectiveTypeEnum(ObjectiveType.TRAVELOBJECTIVE);
        this.setDescription("Walk "+this.getMaxTasks()/1000+"Km. ");
    }

    @Override
    public void areTasksDone() {
        if (this.getTasksDone()==this.getMaxTasks()){
            this.setCompleted(true);
        }
    }

    @Override
    public void checkObjective(String action) {

    }

    @Override
    public void checkObjective(Creature creature) {

    }

    @Override
    public void checkObjective() {
        if (this.getTasksDone()==this.getMaxTasks()){

            this.areTasksDone();
        }
    }

    public void increaseDoneTasksAsMeters(int meters){
        this.setTasksDone(this.getTasksDone()+meters);
    }

}
