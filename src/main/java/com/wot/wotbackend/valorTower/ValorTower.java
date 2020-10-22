package com.wot.wotbackend.valorTower;

import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.creatureModel.creatureClan.Lemesur;
import com.wot.wotbackend.creatureModel.types.Undead;
import com.wot.wotbackend.itemModel.GearModels.Weapon;
import com.wot.wotbackend.itemModel.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValorTower {

    private static volatile ValorTower valorTower = new ValorTower();

    private List<Creature> floorList = new ArrayList<>();

    private ValorTower(){
        initCreaturesToFloorList();
    }

    public static ValorTower getInstance(){
        return valorTower;
    }

    private void initCreaturesToFloorList(){
        this.floorList.add(new Creature("Tower Guard", Undead.getInstance(),Lemesur.getInstance(),false));
        for (int i = 1; i <= 100 ; i++) {
            if(i % 5==0){
                Creature creature = new Creature("Tower Warden", Undead.getInstance(),Lemesur.getInstance(),true,i);
                creature.getItems().add(Item.createRandomItem(i));
                this.floorList.add(creature);
            } else{
                Creature creature = new Creature("Tower Guard", Undead.getInstance(),Lemesur.getInstance(),true,i);
                creature.getItems().add(Item.createRandomItem(i));
                this.floorList.add(creature);
            }
        }

    }

}
