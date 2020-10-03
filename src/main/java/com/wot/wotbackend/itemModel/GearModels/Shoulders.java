package com.wot.wotbackend.itemModel.GearModels;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.itemModel.Item;
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
            shoulders.setItemType("Shoulders");
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
            }
            else if(shouldersAverageStat>level+10 && shouldersAverageStat<=level+17){
                shoulders.setItemName("Shoulders");
            }
            else if(shouldersAverageStat>level+17 && shouldersAverageStat<=(level+23)){
                shoulders.setItemName("Fine Shoulders");
            }
            else{
                shoulders.setItemName("Excellent Shoulders");
            }
        } else{
            shoulders.setItemType("Shoulders");
            int randLvlReq = shoulders.randomWithRange(level-1,level+1);
            shoulders.setLevelRequired(randLvlReq);
            shoulders.setAttackModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setMagicAttackModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setHpModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setDefenceModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            shoulders.setMagicDefenceModifier(shoulders.randomWithRange(randLvlReq,randLvlReq+25));
            int ShouldersAverageStat= (shoulders.getHpModifier()+shoulders.getAttackModifier()+shoulders.getMagicAttackModifier()+shoulders.getDefenceModifier()+shoulders.getMagicDefenceModifier())/5;
            if(ShouldersAverageStat>=level && ShouldersAverageStat<=level+10){
                shoulders.setItemName("Rusty Shoulders");
            }
            else if(ShouldersAverageStat>level+10 && ShouldersAverageStat<=level+17){
                shoulders.setItemName("Shoulders");
            }
            else if(ShouldersAverageStat>level+17 && ShouldersAverageStat<=(level+23)){
                shoulders.setItemName("Fine Shoulders");
            }
            else{
                shoulders.setItemName("Excellent Shoulders");
            }
        }
        return shoulders;
    }
}
