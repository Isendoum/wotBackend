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
public class Amulet extends Item {
    public Amulet createRandomAmulet(int level){
        Amulet amulet= new Amulet();
        if(level==1){
            amulet.setItemType(ItemType.AMULET);
            int randLvlReq = amulet.randomWithRange(level,level+1);
            amulet.setLevelRequired(randLvlReq);
            amulet.setAttackModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            amulet.setMagicAttackModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            amulet.setHpModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            amulet.setDefenceModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            amulet.setMagicDefenceModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            int amuletAverageStat= (amulet.getHpModifier()+amulet.getAttackModifier()+amulet.getMagicAttackModifier()+amulet.getDefenceModifier()+amulet.getMagicDefenceModifier())/5;
            if(amuletAverageStat>=level && amuletAverageStat<=level+10){
                amulet.setItemName("Rusty Amulet");
                amulet.setGoldValue((Math.round((float)amuletAverageStat/4)*level));
            }
            else if(amuletAverageStat>level+10 && amuletAverageStat<=level+17){
                amulet.setItemName("Amulet");
                amulet.setGoldValue((Math.round((float)amuletAverageStat/3)*level));
            }
            else if(amuletAverageStat>level+17 && amuletAverageStat<=level+23){
                amulet.setItemName("Fine Amulet");
                amulet.setGoldValue((Math.round((float)amuletAverageStat/2)*level));
            }
            else{
                amulet.setItemName("Excellent Amulet");
                amulet.setGoldValue(amuletAverageStat*level);
            }
        } else{
            amulet.setItemType(ItemType.AMULET);
            int randLvlReq = amulet.randomWithRange(level-1,level+1);
            amulet.setLevelRequired(randLvlReq);
            amulet.setAttackModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            amulet.setMagicAttackModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            amulet.setHpModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            amulet.setDefenceModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            amulet.setMagicDefenceModifier(amulet.randomWithRange(randLvlReq,randLvlReq+25));
            int amuletAverageStat= (amulet.getHpModifier()+amulet.getAttackModifier()+amulet.getMagicAttackModifier()+amulet.getDefenceModifier()+amulet.getMagicDefenceModifier())/5;
            if(amuletAverageStat>=level && amuletAverageStat<=level+10){
                amulet.setItemName("Rusty Amulet");
                amulet.setGoldValue((Math.round((float)amuletAverageStat/4)*level));
            }
            else if(amuletAverageStat>level+10 && amuletAverageStat<=level+17){
                amulet.setItemName("Amulet");
                amulet.setGoldValue((Math.round((float)amuletAverageStat/3)*level));
            }
            else if(amuletAverageStat>level+17 && amuletAverageStat<=level+23){
                amulet.setItemName("Fine Amulet");
                amulet.setGoldValue((Math.round((float)amuletAverageStat/2)*level));
            }
            else{
                amulet.setItemName("Excellent Amulet");
                amulet.setGoldValue(amuletAverageStat*level);
            }
        }
        return amulet;
    }
}
