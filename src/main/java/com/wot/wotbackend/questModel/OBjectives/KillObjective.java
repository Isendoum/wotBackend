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
import org.springframework.context.annotation.Bean;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize
@JsonSerialize

public class KillObjective extends Objective implements IObjective {

    private String creatureName;

    public KillObjective(String creatureName, int maxTasks){
        this.setMaxTasks(maxTasks);
        this.creatureName = creatureName;
        this.setObjectiveTypeEnum(ObjectiveType.KILLOBJECTIVE);
        this.setDescription("Kill "+this.getMaxTasks()+" "+this.creatureName);
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
        if (this.creatureName.equals(creature.getName())){
            this.increaseTasksDone();
            this.areTasksDone();
        } else if(this.creatureName.equals("creature")){
            this.increaseTasksDone();
            this.areTasksDone();
        }
    }

    @Override
    public void checkObjective() {

    }



}
