package com.wot.wotbackend.itemModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.wot.wotbackend.itemModel.ConsumableModel.ConsumableType;
import com.wot.wotbackend.itemModel.ConsumableModel.IpPotion;
import com.wot.wotbackend.itemModel.GearModels.*;
import com.wot.wotbackend.itemModel.ConsumableModel.Potion;
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
    private int goldValue;





    //increases item quantity
    public void increaseQuantity(){
        if(this.getQuantity()<99){
            this.setQuantity(this.getQuantity()+1);
        }

    }

    //decreases item quantity
    public void decreaseQuantity(){
        if(this.getQuantity()>0){
            this.setQuantity(this.getQuantity()-1);
        }

    }

    //creates a random item based on level
    public static Item createRandomItem(int level) {


        String itemType = ItemType.getRandomItemType().toString();

        switch (itemType) {
            case "WEAPON": {
                return new Weapon().createRandomWeapon(level);
            }
            case "OFFHAND": {

                return new OffHand().createRandomOffHand(level);
            }
            case "HELMET": {

                return new Helmet().createRandomHelmet(level);
            }
            case "CHEST": {

                return new Chest().createRandomChest(level);
            }
            case "PANTS": {

                return new Pants().createRandomPants(level);
            }
            case "SHOULDERS": {

                return new Shoulders().createRandomShoulders(level);
            }
            case "GLOVES": {

                return new Gloves().createRandomGloves(level);
            }
            case "BOOTS": {

                return new Boots().createRandomBoots(level);
            }
            case "AMULET": {

                return new Amulet().createRandomAmulet(level);
            }
            case "RING": {

                return new Ring().createRandomRing(level);
            }
            default:
                String consumableType = ConsumableType.getRandomConsumableType().toString();
                if(consumableType.equals("POTION")){
                    return new Potion();
                }else{
                    return new IpPotion();
                }
        }
    }

    //returns a random number from min to max
    public int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }


}
