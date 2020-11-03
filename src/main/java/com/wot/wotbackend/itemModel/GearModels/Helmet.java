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
public class Helmet extends Item {

    public Helmet createRandomHelmet(int level){
        Helmet helmet= new Helmet();
        if(level==1){

            helmet.setItemType(ItemType.HELMET);
            int randLvlReq = helmet.randomWithRange(level,level+1);
            helmet.setLevelRequired(randLvlReq);
            helmet.setAttackModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            helmet.setMagicAttackModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            helmet.setHpModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            helmet.setDefenceModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            helmet.setMagicDefenceModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            int helmetAverageStat= (helmet.getHpModifier()+helmet.getAttackModifier()+helmet.getMagicAttackModifier()+helmet.getDefenceModifier()+helmet.getMagicDefenceModifier())/5;
            if(helmetAverageStat>=level && helmetAverageStat<=level+10){
                helmet.setItemName("Rusty Helmet");
                helmet.setGoldValue((Math.round((float)helmetAverageStat/4)*level));
            }
            else if(helmetAverageStat>level+10 && helmetAverageStat<=level+17){
                helmet.setItemName("Helmet");
                helmet.setGoldValue((Math.round((float)helmetAverageStat/3)*level));
            }
            else if(helmetAverageStat>level+17 && helmetAverageStat<=level+23){
                helmet.setItemName("Fine Helmet");
                helmet.setGoldValue((Math.round((float)helmetAverageStat/2)*level));
            }
            else{
                helmet.setItemName("Excellent Helmet");
                helmet.setGoldValue(helmetAverageStat*level);
            }
        } else{

            helmet.setItemType(ItemType.HELMET);
            int randLvlReq = helmet.randomWithRange(level-1,level+1);
            helmet.setLevelRequired(randLvlReq);
            helmet.setAttackModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            helmet.setMagicAttackModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            helmet.setHpModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            helmet.setDefenceModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            helmet.setMagicDefenceModifier(helmet.randomWithRange(randLvlReq,randLvlReq+25));
            int helmetAverageStat= (helmet.getHpModifier()+helmet.getAttackModifier()+helmet.getMagicAttackModifier()+helmet.getDefenceModifier()+helmet.getMagicDefenceModifier())/5;
            if(helmetAverageStat>=level && helmetAverageStat<=level+10){
                helmet.setItemName("Rusty Helmet");
                helmet.setGoldValue((Math.round((float)helmetAverageStat/4)*level));
            }
            else if(helmetAverageStat>level+10 && helmetAverageStat<=level+17){
                helmet.setItemName("Helmet");
                helmet.setGoldValue((Math.round((float)helmetAverageStat/3)*level));
            }
            else if(helmetAverageStat>level+17 && helmetAverageStat<=level+23){
                helmet.setItemName("Fine Helmet");
                helmet.setGoldValue((Math.round((float)helmetAverageStat/2)*level));
            }
            else{
                helmet.setItemName("Excellent Helmet");
                helmet.setGoldValue(helmetAverageStat*level);
            }
        }

        return helmet;
    }

}
