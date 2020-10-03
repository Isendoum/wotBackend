package com.wot.wotbackend.itemModel.GearModels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.itemModel.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@JsonDeserialize
public class Chest extends Item {

    public Chest createRandomChest(int level){
        Chest chest= new Chest();
        if(level==1){
            chest.setItemType("Chest");
            int randLvlReq = chest.randomWithRange(level,level+1);
            chest.setLevelRequired(randLvlReq);
            chest.setAttackModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            chest.setMagicAttackModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            chest.setHpModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            chest.setDefenceModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            chest.setMagicDefenceModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            int chestAverageStat= (chest.getHpModifier()+chest.getAttackModifier()+chest.getMagicAttackModifier()+chest.getDefenceModifier()+chest.getMagicDefenceModifier())/5;
            if(chestAverageStat>=level && chestAverageStat<=level+10){
                chest.setItemName("Rusty Chest");
            }
            else if(chestAverageStat>level+10 && chestAverageStat<=level+17){
                chest.setItemName("Chest");
            }
            else if(chestAverageStat>level+17 && chestAverageStat<=level+23){
                chest.setItemName("Fine Chest");
            }
            else{
                chest.setItemName("Excellent Chest");
            }
        } else{
            chest.setItemType("Chest");
            int randLvlReq = chest.randomWithRange(level-1,level+1);
            chest.setLevelRequired(randLvlReq);
            chest.setAttackModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            chest.setMagicAttackModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            chest.setHpModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            chest.setDefenceModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            chest.setMagicDefenceModifier(chest.randomWithRange(randLvlReq,randLvlReq+25));
            int chestAverageStat= (chest.getHpModifier()+chest.getAttackModifier()+chest.getMagicAttackModifier()+chest.getDefenceModifier()+chest.getMagicDefenceModifier())/5;
            if(chestAverageStat>=level && chestAverageStat<=level+10){
                chest.setItemName("Rusty Chest");
            }
            else if(chestAverageStat>level+10 && chestAverageStat<=level+17){
                chest.setItemName("Chest");
            }
            else if(chestAverageStat>level+17 && chestAverageStat<=level+23){
                chest.setItemName("Fine Chest");
            }
            else{
                chest.setItemName("Excellent Chest");
            }
        }
        return chest;
    }
}
