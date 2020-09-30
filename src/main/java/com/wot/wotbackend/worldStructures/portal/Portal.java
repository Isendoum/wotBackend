package com.wot.wotbackend.worldStructures.portal;

import com.wot.wotbackend.creatureModel.Creature;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Data

public class Portal extends StructureModel {

    List<Creature> creatureList=new ArrayList<>();


    public Portal(){
        this.setStructureType("Portal");

    }



}
