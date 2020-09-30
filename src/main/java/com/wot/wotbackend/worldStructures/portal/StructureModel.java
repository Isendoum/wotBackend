package com.wot.wotbackend.worldStructures.portal;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor

public abstract class StructureModel {

    private String structureType;
    private double latitude;
    private double longitude;

}
