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
public class Pants extends Item {

    public Pants createRandomPants(int level){
        Pants pants= new Pants();
        if(level==1){
            pants.setItemType(ItemType.PANTS);
            int randLvlReq =  pants.randomWithRange(level,level+1);
            pants.setLevelRequired(randLvlReq);
            pants.setAttackModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            pants.setMagicAttackModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            pants.setHpModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            pants.setDefenceModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            pants.setMagicDefenceModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            int pantsAverageStat= (pants.getHpModifier()+pants.getAttackModifier()+pants.getMagicAttackModifier()+pants.getDefenceModifier()+pants.getMagicDefenceModifier())/5;
            if(pantsAverageStat>=level && pantsAverageStat<=level+10){
                pants.setItemName("Rusty Pants");
                pants.setGoldValue((Math.round((float)pantsAverageStat/4)*level));
            }
            else if(pantsAverageStat>level+10 && pantsAverageStat<=level+17){
                pants.setItemName("Pants");
                pants.setGoldValue((Math.round((float)pantsAverageStat/3)*level));
            }
            else if(pantsAverageStat>level+17 && pantsAverageStat<=level+23){
                pants.setItemName("Fine Pants");
                pants.setGoldValue((Math.round((float)pantsAverageStat/2)*level));
            }
            else{
                pants.setItemName("Excellent Pants");
                pants.setGoldValue(pantsAverageStat*level);
            }
        } else{
            pants.setItemType(ItemType.PANTS);
            int randLvlReq = pants.randomWithRange(level-1,level+1);
            pants.setLevelRequired(randLvlReq);
            pants.setAttackModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            pants.setMagicAttackModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            pants.setHpModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            pants.setDefenceModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            pants.setMagicDefenceModifier(pants.randomWithRange(randLvlReq,randLvlReq+25));
            int pantsAverageStat= (pants.getHpModifier()+pants.getAttackModifier()+pants.getMagicAttackModifier()+pants.getDefenceModifier()+pants.getMagicDefenceModifier())/5;
            if(pantsAverageStat>=level && pantsAverageStat<=level+10){
                pants.setItemName("Rusty Pants");
                pants.setGoldValue((Math.round((float)pantsAverageStat/4)*level));
            }
            else if(pantsAverageStat>level+10 && pantsAverageStat<=level+17){
                pants.setItemName("Pants");
                pants.setGoldValue((Math.round((float)pantsAverageStat/3)*level));
            }
            else if(pantsAverageStat>level+17 && pantsAverageStat<=level+23){
                pants.setItemName("Fine Pants");
                pants.setGoldValue((Math.round((float)pantsAverageStat/2)*level));
            }
            else{
                pants.setItemName("Excellent Pants");
                pants.setGoldValue(pantsAverageStat*level);
            }
        }
        return pants;
    }
}
