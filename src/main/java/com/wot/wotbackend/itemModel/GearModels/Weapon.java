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
public class Weapon extends Item {

    public Weapon createRandomWeapon(int level){
        Weapon weapon= new Weapon();
        if(level==1){
            weapon.setItemType(ItemType.WEAPON);
            int randLvlReq = weapon.randomWithRange(level,level+1);
            weapon.setLevelRequired(randLvlReq);
            weapon.setAttackModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            weapon.setMagicAttackModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            weapon.setHpModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            weapon.setDefenceModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            weapon.setMagicDefenceModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            int weaponAverageStat= (weapon.getHpModifier()+weapon.getAttackModifier()+weapon.getMagicAttackModifier()+weapon.getDefenceModifier()+weapon.getMagicDefenceModifier())/5;
            if(weaponAverageStat>=level && weaponAverageStat<=level+10){
                weapon.setItemName("Rusty Sword");
                weapon.setGoldValue((Math.round((float)weaponAverageStat/4)*level));
            }
            else if(weaponAverageStat>level+10 && weaponAverageStat<=level+17){
                weapon.setItemName("Sword");
                weapon.setGoldValue((Math.round((float)weaponAverageStat/3)*level));
            }
            else if(weaponAverageStat>level+17 && weaponAverageStat<=(level+23)){
                weapon.setItemName("Fine Sword");
                weapon.setGoldValue((Math.round((float)weaponAverageStat/2)*level));
            }
            else{
                weapon.setItemName("Excellent Sword");
                weapon.setGoldValue(weaponAverageStat*level);
            }
        } else{
            weapon.setItemType(ItemType.WEAPON);
            int randLvlReq = weapon.randomWithRange(level-1,level+1);
            weapon.setLevelRequired(randLvlReq);
            weapon.setAttackModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            weapon.setMagicAttackModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            weapon.setHpModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            weapon.setDefenceModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            weapon.setMagicDefenceModifier(weapon.randomWithRange(randLvlReq,randLvlReq+25));
            int weaponAverageStat= (weapon.getHpModifier()+weapon.getAttackModifier()+weapon.getMagicAttackModifier()+weapon.getDefenceModifier()+weapon.getMagicDefenceModifier())/5;
            if(weaponAverageStat>=level && weaponAverageStat<=level+10){
                weapon.setItemName("Rusty Sword");
                weapon.setGoldValue((Math.round((float)weaponAverageStat/4)*level));
            }
            else if(weaponAverageStat>level+10 && weaponAverageStat<=level+17){
                weapon.setItemName("Sword");
                weapon.setGoldValue((Math.round((float)weaponAverageStat/3)*level));
            }
            else if(weaponAverageStat>level+17 && weaponAverageStat<=(level+23)){
                weapon.setItemName("Fine Sword");
                weapon.setGoldValue((Math.round((float)weaponAverageStat/2)*level));
            }
            else{
                weapon.setItemName("Excellent Sword");
                weapon.setGoldValue(weaponAverageStat*level);
            }
        }
        return weapon;
    }

}
