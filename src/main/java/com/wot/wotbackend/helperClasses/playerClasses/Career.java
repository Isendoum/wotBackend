package com.wot.wotbackend.helperClasses.playerClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Career {

    private int distanceTraveled;
    private int creaturesKilled;
    private int bossesKilled;
    private int currentValorTowerFloor;
    private int topValorTowerFloor;
    private int totalGoldAcquired;
    private int totalGoldSpent;
    private int totalDeaths;
    private int totalLootedItems;


    public void increaseDistanceTraveled(int distanceTraveled){
        this.distanceTraveled= this.distanceTraveled+distanceTraveled;
    }
    public void increaseCreatureKills(){
        this.creaturesKilled++;
    }
    public void increasePlayerDeaths(){
        this.totalDeaths++;
    }

    public void increaseCurrentValorFloor(){
        this.currentValorTowerFloor++;
        if(this.currentValorTowerFloor>this.topValorTowerFloor){
            this.topValorTowerFloor=this.currentValorTowerFloor;
        }
    }
}
