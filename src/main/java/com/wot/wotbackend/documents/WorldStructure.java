package com.wot.wotbackend.documents;

import com.wot.wotbackend.worldStructures.portal.StructureModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class WorldStructure {

    @Id
    private String id;
    private StructureModel structureModel;

    public WorldStructure(StructureModel structure){
        this.structureModel= structure;

    }
}
