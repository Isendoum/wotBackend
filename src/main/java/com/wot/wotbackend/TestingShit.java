package com.wot.wotbackend;

import com.wot.wotbackend.itemModel.GearModels.Shoulders;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.itemModel.ItemType;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TestingShit {
    public static void main(String[] args) {
        /*for (int i = 1; i < 1001; i++) {
            Double exp=100* Math.pow(i,2.5);
            System.out.println("Exp needed for level: "+i+" "+exp.longValue());

        }*/
        int fn;
        int attack=400;
        int defence=150;
        fn= (int) (attack/Math.log(defence));
        System.out.println(fn);

    }
}
