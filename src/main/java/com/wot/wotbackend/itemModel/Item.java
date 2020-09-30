package com.wot.wotbackend.itemModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.wot.wotbackend.itemModel.GearModels.*;
import com.wot.wotbackend.itemModel.Items.Potion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;




@Data
@NoArgsConstructor
@JsonDeserialize
public class Item   {
    private String id= UUID.randomUUID().toString();
    private String itemName;
    private ItemAbility itemAbility;
    private String itemType;
    private int quantity;
    private int levelRequired;
    private int defenceModifier;
    private int attackModifier;
    private int magicDefenceModifier;
    private int magicAttackModifier;
    private int hpModifier;




    public void increaseQuantity(){
        if(this.getQuantity()<99){
            this.setQuantity(this.getQuantity()+1);
        }

    }

    public void decreaseQuantity(){
        if(this.getQuantity()>0){
            this.setQuantity(this.getQuantity()-1);
        }

    }


    public static Item createRandomItem(int level){


        String itemType= ItemType.getRandomItemType().toString();

        switch (itemType) {
            case "WEAPON": {
                Weapon weapon = new Weapon();
                weapon.setLevelRequired(level);
                weapon.setItemName("Rusty Sword");
                weapon.setItemType("Weapon");
                int rand = (int) (Math.random() * 10) + 1;
                weapon.setAttackModifier(rand * weapon.getLevelRequired());
                return weapon;
            }
            case "OFFHAND": {
                OffHand offHand = new OffHand();
                offHand.setLevelRequired(level);
                offHand.setItemName("Small Shield");
                offHand.setItemType("Off-Hand");
                int rand = (int) (Math.random() * 10) + 1;
                offHand.setDefenceModifier(rand * offHand.getLevelRequired());
                return offHand;
            }
            case "HELMET": {
                Helmet helmet = new Helmet();
                helmet.setLevelRequired(level);
                helmet.setItemName("Rusty Helmet");
                helmet.setItemType("Helmet");
                int rand = (int) (Math.random() * 10) + 1;
                helmet.setDefenceModifier(rand * helmet.getLevelRequired());
                helmet.setHpModifier(rand * helmet.getLevelRequired());
                return helmet;
            }
            case "CHEST": {
                Chest chest = new Chest();
                chest.setLevelRequired(level);
                chest.setItemName("Rusty Chest");
                chest.setItemType("Chest");
                int rand = (int) (Math.random() * 10) + 1;
                chest.setDefenceModifier(rand * chest.getLevelRequired());
                chest.setHpModifier(rand * chest.getLevelRequired());
                return chest;
            }
            case "PANTS": {
                Pants pants = new Pants();
                pants.setLevelRequired(level);
                pants.setItemName("Rusty Pants");
                pants.setItemType("Pants");
                int rand = (int) (Math.random() * 10) + 1;
                pants.setDefenceModifier(rand * pants.getLevelRequired());
                pants.setHpModifier(rand * pants.getLevelRequired());
                return pants;
            }
            case "SHOULDERS": {
                Shoulders shoulders = new Shoulders();
                shoulders.setLevelRequired(level);
                shoulders.setItemName("Rusty Shoulders");
                shoulders.setItemType("Shoulders");
                int rand = (int) (Math.random() * 10) + 1;
                shoulders.setDefenceModifier(rand * shoulders.getLevelRequired());
                shoulders.setHpModifier(rand * shoulders.getLevelRequired());
                return shoulders;
            }
            case "GLOVES": {
                Gloves gloves = new Gloves();
                gloves.setLevelRequired(level);
                gloves.setItemName("Rusty Gloves");
                gloves.setItemType("Gloves");
                int rand = (int) (Math.random() * 10) + 1;
                gloves.setDefenceModifier(rand * gloves.getLevelRequired());
                gloves.setHpModifier(rand * gloves.getLevelRequired());
                return gloves;
            }
            case "BOOTS": {
                Boots boots = new Boots();
                boots.setLevelRequired(level);
                boots.setItemName("Rusty Boots");
                boots.setItemType("Boots");
                int rand = (int) (Math.random() * 10) + 1;
                boots.setDefenceModifier(rand * boots.getLevelRequired());
                boots.setHpModifier(rand * boots.getLevelRequired());
                return boots;
            }
            case "AMULET": {
                Amulet amulet = new Amulet();
                amulet.setLevelRequired(level);
                amulet.setItemName("Dusty Amulet");
                amulet.setItemType("Amulet");
                int rand = (int) (Math.random() * 10) + 1;
                amulet.setMagicDefenceModifier(rand * amulet.getLevelRequired());
                return amulet;
            }
            case "RING": {
                Ring ring = new Ring();
                ring.setLevelRequired(level);
                ring.setItemName("Tin Ring");
                ring.setItemType("Ring");
                int rand = (int) (Math.random() * 10) + 1;
                ring.setMagicDefenceModifier(rand * ring.getLevelRequired());
                return ring;
            }
            default:
                Potion potion = new Potion();
                potion.increaseQuantity();

                return potion;
        }


    }


}
