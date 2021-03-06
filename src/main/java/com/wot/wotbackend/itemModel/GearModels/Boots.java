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
public class Boots extends Item {
    public Boots createRandomBoots(int level){
        Boots boots= new Boots();
        if(level==1){
            boots.setItemType(ItemType.BOOTS);
            int randLvlReq = boots.randomWithRange(level,level+1);
            boots.setLevelRequired(randLvlReq);
            boots.setAttackModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            boots.setMagicAttackModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            boots.setHpModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            boots.setDefenceModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            boots.setMagicDefenceModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            int bootsAverageStat= (boots.getHpModifier()+boots.getAttackModifier()+boots.getMagicAttackModifier()+boots.getDefenceModifier()+boots.getMagicDefenceModifier())/5;
            if(bootsAverageStat>=level && bootsAverageStat<=level+10){
                boots.setItemName("Rusty Boots");
                boots.setGoldValue((Math.round((float)bootsAverageStat/4)*level));
            }
            else if(bootsAverageStat>level+10 && bootsAverageStat<=level+17){
                boots.setItemName("Boots");
                boots.setGoldValue((Math.round((float)bootsAverageStat/3)*level));
            }
            else if(bootsAverageStat>level+17 && bootsAverageStat<=level+23){
                boots.setItemName("Fine Boots");
                boots.setGoldValue((Math.round((float)bootsAverageStat/2)*level));
            }
            else{
                boots.setItemName("Excellent Boots");
                boots.setGoldValue(bootsAverageStat/4*level);
            }
        } else{
            boots.setItemType(ItemType.BOOTS);
            int randLvlReq = boots.randomWithRange(level-1,level+1);
            boots.setLevelRequired(randLvlReq);
            boots.setAttackModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            boots.setMagicAttackModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            boots.setHpModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            boots.setDefenceModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            boots.setMagicDefenceModifier(boots.randomWithRange(randLvlReq,randLvlReq+25));
            int bootsAverageStat= (boots.getHpModifier()+boots.getAttackModifier()+boots.getMagicAttackModifier()+boots.getDefenceModifier()+boots.getMagicDefenceModifier())/5;
            if(bootsAverageStat>=level && bootsAverageStat<=level+10){
                boots.setItemName("Rusty Boots");
                boots.setGoldValue((Math.round((float)bootsAverageStat/4)*level));
            }
            else if(bootsAverageStat>level+10 && bootsAverageStat<=level+17){
                boots.setItemName("Boots");
                boots.setGoldValue((Math.round((float)bootsAverageStat/3)*level));
            }
            else if(bootsAverageStat>level+17 && bootsAverageStat<=level+23){
                boots.setItemName("Fine Boots");
                boots.setGoldValue((Math.round((float)bootsAverageStat/2)*level));
            }
            else{
                boots.setItemName("Excellent Boots");
                boots.setGoldValue(bootsAverageStat/4*level);
            }
        }
        return boots;
    }
}
