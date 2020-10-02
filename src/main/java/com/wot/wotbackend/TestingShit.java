package com.wot.wotbackend;

import com.wot.wotbackend.itemModel.ItemType;

public class TestingShit {
    public static void main(String[] args) {
        /*for (int i = 1; i < 1001; i++) {
            Double exp=100* Math.pow(i,2.5);
            System.out.println("Exp needed for level: "+i+" "+exp.longValue());

        }*/
        int attack = 10;
        int defence =18;

        int hitTaken=  Math.round(attack-(defence*0.1f));
        System.out.println(hitTaken);
    }
}
