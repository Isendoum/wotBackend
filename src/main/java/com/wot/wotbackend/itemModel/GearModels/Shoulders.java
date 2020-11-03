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
public class Shoulders extends Item {

    public Shoulders createRandomShoulders(int level){
        Shoulders shoulders= new Shoulders();
        if(level==1){
            shoulders.setItemType(ItemType.SHOULDERS);
            int randLvlReq = shoulders.randomWithRange(level,level+1);
            shoulders.setLevelRequired(randLvlReq);
            shoulders.setAttackModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setMagicAttackModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setHpModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setDefenceModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setMagicDefenceModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            int shouldersAverageStat= (shoulders.getHpModifier()+shoulders.getAttackModifier()+shoulders.getMagicAttackModifier()+shoulders.getDefenceModifier()+shoulders.getMagicDefenceModifier())/5;
            if(shouldersAverageStat>=level && shouldersAverageStat<=level+10){
                shoulders.setItemName("Rusty Shoulders");
                shoulders.setGoldValue((Math.round((float)shouldersAverageStat/4)*level));
            }
            else if(shouldersAverageStat>level+10 && shouldersAverageStat<=level+17){
                shoulders.setItemName("Shoulders");
                shoulders.setGoldValue((Math.round((float)shouldersAverageStat/3)*level));
            }
            else if(shouldersAverageStat>level+17 && shouldersAverageStat<=(level+23)){
                shoulders.setItemName("Fine Shoulders");
                shoulders.setGoldValue((Math.round((float)shouldersAverageStat/2)*level));
            }
            else{
                shoulders.setItemName("Excellent Shoulders");
                shoulders.setGoldValue(shouldersAverageStat*level);
            }
        } else{
            shoulders.setItemType(ItemType.SHOULDERS);
            int randLvlReq = shoulders.randomWithRange(level-1,level+1);
            shoulders.setLevelRequired(randLvlReq);
            shoulders.setAttackModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setMagicAttackModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setHpModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setDefenceModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setMagicDefenceModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            int shouldersAverageStat= (shoulders.getHpModifier()+shoulders.getAttackModifier()+shoulders.getMagicAttackModifier()+shoulders.getDefenceModifier()+shoulders.getMagicDefenceModifier())/5;
            if(shouldersAverageStat>=level && shouldersAverageStat<=level+10){
                shoulders.setItemName("Rusty Shoulders");
                shoulders.setGoldValue((Math.round((float)shouldersAverageStat/4)*level));
            }
            else if(shouldersAverageStat>level+10 && shouldersAverageStat<=level+17){
                shoulders.setItemName("Shoulders");
                shoulders.setGoldValue((Math.round((float)shouldersAverageStat/3)*level));
            }
            else if(shouldersAverageStat>level+17 && shouldersAverageStat<=(level+23)){
                shoulders.setItemName("Fine Shoulders");
                shoulders.setGoldValue((Math.round((float)shouldersAverageStat/2)*level));
            }
            else{
                shoulders.setItemName("Excellent Shoulders");
                shoulders.setGoldValue(shouldersAverageStat*level);
            }
        }
        return shoulders;
    }
}
