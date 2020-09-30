package com.wot.wotbackend.characterModel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.itemModel.GearModels.Chest;
import com.wot.wotbackend.itemModel.GearModels.Weapon;
import com.wot.wotbackend.itemModel.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@JsonSerialize
public class Inventory<T extends Item>  {
    List<T> inventory = new ArrayList<T>();

    public Inventory(){
    }


    public void add(T item) {
        inventory.add(item);
    }
    public int size(){
        return inventory.size();
    }

    public T get(int i){
        return inventory.get(i);
    }

    public void remove(T t){
        inventory.remove(t);
    }
    public void remove(int i){
        inventory.remove(i);
    }
}
