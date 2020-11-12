package com.wot.wotbackend.questModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
@JsonSerialize
public class Objective {

    private String description;
    private ObjectiveType objectiveTypeEnum;
    private boolean isCompleted;
    private int maxTasks;
    private int tasksDone;


    public void increaseTasksDone(){
        if(this.tasksDone != this.maxTasks){
            this.tasksDone++;
        }

    }
}
