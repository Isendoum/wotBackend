package com.wot.wotbackend.testingEnviroment;

import com.wot.wotbackend.questModel.OBjectives.KillObjective;
import com.wot.wotbackend.questModel.Objective;
import com.wot.wotbackend.questModel.ObjectiveType;
import com.wot.wotbackend.questModel.Quest;
import com.wot.wotbackend.questModel.QuestType;
import org.geojson.*;


public class TestingShit {
    public static void main(String[] args) throws Exception {
        /*for (int i = 1; i < 1001; i++) {
            Double exp=100* Math.pow(i,2.5);
            System.out.println("Exp needed for level: "+i+" "+exp.longValue());

        }*/

        for (int i = 0; i < 10; i++) {
            if(Math.round(Math.log(30)) >= randomWithRange(1,10)){
                System.out.println("crit");
            } else{
                System.out.println("normal dmg");
            }
        }




    }

    private static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}
