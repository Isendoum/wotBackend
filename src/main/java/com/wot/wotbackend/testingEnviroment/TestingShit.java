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

        Gridnode gridnode= new Gridnode(1);

        gridnode.addNode(new Gridnode(2));
        gridnode.addNode(new Gridnode(3));
        gridnode.addNode(new Gridnode(4));
        gridnode.addNode(new Gridnode(5));
        gridnode.printAllNodes();




    }

    private static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}
