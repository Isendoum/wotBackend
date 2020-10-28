package com.wot.wotbackend.questModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.itemModel.ConsumableModel.Potion;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.questModel.OBjectives.KillObjective;
import com.wot.wotbackend.questModel.OBjectives.TravelObjective;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
@JsonSerialize
public class Quest {


    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private List<Objective> objectiveList = new ArrayList<>();
    private int questLevel;
    private int exp;
    private int gold;
    private List<Item> itemRewards = new ArrayList<>();
    private boolean isQuestCompleted;
    private QuestType questTypeEnum;



    public void addObjective(Objective o){
        objectiveList.add(o);
    }

    public void addItemToRewardList(Item item){
        this.itemRewards.add(item);
    }

    public void generateKillQuest(){
        KillObjective killObjective = new KillObjective("creature",1);
        this.setExp(1000);
        this.setGold(4000);
        this.setQuestLevel(1);
        this.setQuestTypeEnum(QuestType.MAINQUEST);
        this.setName("New beginnings");
        this.setDescription("This is a cruel new world. Learn how to fight.");
        this.addItemToRewardList(new Potion());
        this.addObjective(killObjective);
    }

    public void generateTravelQuest(){
        TravelObjective travelObjective = new TravelObjective(1000);
        this.setExp(3000);
        this.setGold(1500);
        this.setQuestLevel(1);
        this.setQuestTypeEnum(QuestType.MAINQUEST);
        this.setName("Wondering hero");
        this.setDescription("The world has changed! Portals have spawned all around. Take a walk and see for yourself.");
        this.addItemToRewardList(new Potion());
        this.addObjective(travelObjective);
    }

    public void checkObjectives(String action){
        int completedObjectives=0;
        for (Objective iObjective : this.objectiveList) {
            if (iObjective instanceof KillObjective) {
                ((KillObjective) iObjective).checkObjective(action);
                if (((KillObjective) iObjective).isCompleted()){
                    completedObjectives++;
                }
            }
        }
        if(completedObjectives==this.objectiveList.size()){
            this.setQuestCompleted(true);
        }
    }

    public void checkObjectives(Creature creature){
        int completedObjectives=0;
        for (Objective iObjective : this.objectiveList) {
            if (iObjective instanceof KillObjective) {
                ((KillObjective) iObjective).checkObjective(creature);
                if (((KillObjective) iObjective).isCompleted()){
                    completedObjectives++;
                }
            }
        }
        if(completedObjectives==this.objectiveList.size()){
            this.setQuestCompleted(true);
        }
    }

    public void checkObjectives(int meters){
        int completedObjectives=0;
        for (Objective iObjective : this.objectiveList) {
            if (iObjective instanceof TravelObjective) {
                ((TravelObjective) iObjective).increaseDoneTasksAsMeters(meters);
                ((TravelObjective) iObjective).checkObjective();
                if ((iObjective).isCompleted()){
                    completedObjectives++;
                }
            }
        }
        if(completedObjectives==this.objectiveList.size()){
            this.setQuestCompleted(true);
        }
    }
}
