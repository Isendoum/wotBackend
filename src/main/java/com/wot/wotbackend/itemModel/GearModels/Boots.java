package com.wot.wotbackend.itemModel.GearModels;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.itemModel.Item;

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
            boots.setItemType("Boots");
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
            }
            else if(bootsAverageStat>level+10 && bootsAverageStat<=level+17){
                boots.setItemName("Boots");
            }
            else if(bootsAverageStat>level+17 && bootsAverageStat<=level+23){
                boots.setItemName("Fine Boots");
            }
            else{
                boots.setItemName("Excellent Boots");
            }
        } else{
            boots.setItemType("Boots");
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
            }
            else if(bootsAverageStat>level+10 && bootsAverageStat<=level+17){
                boots.setItemName("Boots");
            }
            else if(bootsAverageStat>level+17 && bootsAverageStat<=level+23){
                boots.setItemName("Fine Boots");
            }
            else{
                boots.setItemName("Excellent Boots");
            }
        }
        return boots;
    }
}
