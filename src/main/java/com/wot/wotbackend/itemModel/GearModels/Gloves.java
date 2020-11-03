package com.wot.wotbackend.itemModel.GearModels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.itemModel.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
@JsonDeserialize
public class Gloves extends Item {

    public Gloves createRandomGloves(int level){
        Gloves gloves= new Gloves();
        if(level==1){
            gloves.setItemType(ItemType.GLOVES);
            int randLvlReq = gloves.randomWithRange(level,level+1);
            gloves.setLevelRequired(randLvlReq);
            gloves.setAttackModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            gloves.setMagicAttackModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            gloves.setHpModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            gloves.setDefenceModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            gloves.setMagicDefenceModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            int glovesAverageStat= (gloves.getHpModifier()+gloves.getAttackModifier()+gloves.getMagicAttackModifier()+gloves.getDefenceModifier()+gloves.getMagicDefenceModifier())/5;
            if(glovesAverageStat>=level && glovesAverageStat<=level+10){
                gloves.setItemName("Rusty Gloves");
                gloves.setGoldValue((Math.round((float)glovesAverageStat/4)*level));
            }
            else if(glovesAverageStat>level+10 && glovesAverageStat<=level+17){
                gloves.setItemName("Gloves");
                gloves.setGoldValue((Math.round((float)glovesAverageStat/3)*level));
            }
            else if(glovesAverageStat>level+17 && glovesAverageStat<=level+23){
                gloves.setItemName("Fine Gloves");
                gloves.setGoldValue((Math.round((float)glovesAverageStat/2)*level));
            }
            else{
                gloves.setItemName("Excellent Gloves");
                gloves.setGoldValue(glovesAverageStat*level);
            }
        } else{
            gloves.setItemType(ItemType.GLOVES);
            int randLvlReq = gloves.randomWithRange(level-1,level+1);
            gloves.setLevelRequired(randLvlReq);
            gloves.setAttackModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            gloves.setMagicAttackModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            gloves.setHpModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            gloves.setDefenceModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            gloves.setMagicDefenceModifier(gloves.randomWithRange(randLvlReq,randLvlReq+25));
            int glovesAverageStat= (gloves.getHpModifier()+gloves.getAttackModifier()+gloves.getMagicAttackModifier()+gloves.getDefenceModifier()+gloves.getMagicDefenceModifier())/5;
            if(glovesAverageStat>=level && glovesAverageStat<=level+10){
                gloves.setItemName("Rusty Gloves");
                gloves.setGoldValue((Math.round((float)glovesAverageStat/4)*level));
            }
            else if(glovesAverageStat>level+10 && glovesAverageStat<=level+17){
                gloves.setItemName("Gloves");
                gloves.setGoldValue((Math.round((float)glovesAverageStat/3)*level));
            }
            else if(glovesAverageStat>level+17 && glovesAverageStat<=level+23){
                gloves.setItemName("Fine Gloves");
                gloves.setGoldValue((Math.round((float)glovesAverageStat/2)*level));
            }
            else{
                gloves.setItemName("Excellent Gloves");
                gloves.setGoldValue(glovesAverageStat*level);
            }
        }
        return gloves;
    }
}
