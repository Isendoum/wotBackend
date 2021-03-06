package com.wot.wotbackend.itemModel.GearModels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.itemModel.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonDeserialize
public class OffHand extends Item {

    public OffHand createRandomOffHand(int level){
        OffHand offHand= new OffHand();
        if(level==1){
            offHand.setItemType(ItemType.OFFHAND);
            int randLvlReq = offHand.randomWithRange(level,level+1);
            offHand.setLevelRequired(randLvlReq);
            offHand.setAttackModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            offHand.setMagicAttackModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            offHand.setHpModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            offHand.setDefenceModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            offHand.setMagicDefenceModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            int offHandAverageStat= (offHand.getHpModifier()+offHand.getAttackModifier()+offHand.getMagicAttackModifier()+offHand.getDefenceModifier()+offHand.getMagicDefenceModifier())/5;
            if(offHandAverageStat>=level && offHandAverageStat<=level+10){
                offHand.setItemName("Rusty Shield");
                offHand.setGoldValue((Math.round((float)offHandAverageStat/4)*level));
            }
            else if(offHandAverageStat>level+10 && offHandAverageStat<=level+17){
                offHand.setItemName("Shield");
                offHand.setGoldValue((Math.round((float)offHandAverageStat/3)*level));
            }
            else if(offHandAverageStat>level+17 && offHandAverageStat<=level+23){
                offHand.setItemName("Fine Shield");
                offHand.setGoldValue((Math.round((float)offHandAverageStat/2)*level));
            }
            else{
                offHand.setItemName("Excellent Shield");
                offHand.setGoldValue(offHandAverageStat*level);
            }
        } else{
            offHand.setItemType(ItemType.OFFHAND);
            int randLvlReq = offHand.randomWithRange(level-1,level+1);
            offHand.setLevelRequired(randLvlReq);
            offHand.setAttackModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            offHand.setMagicAttackModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            offHand.setHpModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            offHand.setDefenceModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            offHand.setMagicDefenceModifier(offHand.randomWithRange(randLvlReq,randLvlReq+25));
            int offHandAverageStat= (offHand.getHpModifier()+offHand.getAttackModifier()+offHand.getMagicAttackModifier()+offHand.getDefenceModifier()+offHand.getMagicDefenceModifier())/5;
            if(offHandAverageStat>=level && offHandAverageStat<=level+10){
                offHand.setItemName("Rusty Shield");
                offHand.setGoldValue((Math.round((float)offHandAverageStat/4)*level));
            }
            else if(offHandAverageStat>level+10 && offHandAverageStat<=level+17){
                offHand.setItemName("Shield");
                offHand.setGoldValue((Math.round((float)offHandAverageStat/3)*level));
            }
            else if(offHandAverageStat>level+17 && offHandAverageStat<=level+23){
                offHand.setItemName("Fine Shield");
                offHand.setGoldValue((Math.round((float)offHandAverageStat/2)*level));
            }
            else{
                offHand.setItemName("Excellent Shield");
                offHand.setGoldValue(offHandAverageStat*level);
            }
        }
        return offHand;
    }

}
