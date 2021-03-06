package com.wot.wotbackend.itemModel.GearModels;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.itemModel.Item;

import com.wot.wotbackend.itemModel.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@AllArgsConstructor
@JsonDeserialize
public class Ring extends Item {


    public Ring createRandomRing(int level){
        Ring ring= new Ring();
        if(level==1){
            ring.setItemType(ItemType.RING);
            int randLvlReq = ring.randomWithRange(level,level+1);
            ring.setLevelRequired(randLvlReq);
            ring.setAttackModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            ring.setMagicAttackModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            ring.setHpModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            ring.setDefenceModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            ring.setMagicDefenceModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            int ringAverageStat= (ring.getHpModifier()+ring.getAttackModifier()+ring.getMagicAttackModifier()+ring.getDefenceModifier()+ring.getMagicDefenceModifier())/5;
            if(ringAverageStat>=level && ringAverageStat<=level+10){
                ring.setItemName("Rusty Ring");
                ring.setGoldValue((Math.round((float)ringAverageStat/4)*level));
            }
            else if(ringAverageStat>level+10 && ringAverageStat<=level+17){
                ring.setItemName("Ring");
                ring.setGoldValue((Math.round((float)ringAverageStat/3)*level));
            }
            else if(ringAverageStat>level+17 && ringAverageStat<=level+23){
                ring.setItemName("Fine Ring");
                ring.setGoldValue((Math.round((float)ringAverageStat/2)*level));
            }
            else{
                ring.setItemName("Excellent Ring");
                ring.setGoldValue(ringAverageStat*level);
            }
        } else{
            ring.setItemType(ItemType.RING);
            int randLvlReq = ring.randomWithRange(level-1,level+1);
            ring.setLevelRequired(randLvlReq);
            ring.setAttackModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            ring.setMagicAttackModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            ring.setHpModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            ring.setDefenceModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            ring.setMagicDefenceModifier(ring.randomWithRange(randLvlReq,randLvlReq+25));
            int ringAverageStat= (ring.getHpModifier()+ring.getAttackModifier()+ring.getMagicAttackModifier()+ring.getDefenceModifier()+ring.getMagicDefenceModifier())/5;
            if(ringAverageStat>=level && ringAverageStat<=level+10){
                ring.setItemName("Rusty Ring");
                ring.setGoldValue((Math.round((float)ringAverageStat/4)*level));
            }
            else if(ringAverageStat>level+10 && ringAverageStat<=level+17){
                ring.setItemName("Ring");
                ring.setGoldValue((Math.round((float)ringAverageStat/3)*level));
            }
            else if(ringAverageStat>level+17 && ringAverageStat<=level+23){
                ring.setItemName("Fine Ring");
                ring.setGoldValue((Math.round((float)ringAverageStat/2)*level));
            }
            else{
                ring.setItemName("Excellent Ring");
                ring.setGoldValue(ringAverageStat*level);
            }
        }
        return ring;
    }
}
