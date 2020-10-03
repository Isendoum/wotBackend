package com.wot.wotbackend;

import com.wot.wotbackend.itemModel.GearModels.Shoulders;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.itemModel.ItemType;

public class TestingShit {
    public static void main(String[] args) {
        /*for (int i = 1; i < 1001; i++) {
            Double exp=100* Math.pow(i,2.5);
            System.out.println("Exp needed for level: "+i+" "+exp.longValue());

        }*/
        int po ;
        for (int i = 0; i < 15 ; i++) {
            po = new Item().randomWithRange(13-1,13+1);
            System.out.println(po);
        }

    }
}
