package com.wot.wotbackend.testingEnviroment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.creatureModel.creatureClan.Lemesur;
import com.wot.wotbackend.creatureModel.types.Undead;
import com.wot.wotbackend.documents.WorldStructure;
import com.wot.wotbackend.repositories.WorldStructureRepository;
import com.wot.wotbackend.services.location.LocationModel;
import com.wot.wotbackend.services.location.RandomLocation;
import com.wot.wotbackend.worldStructures.portal.Portal;
import org.geojson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReadJsonFile {



    @Autowired
    WorldStructureRepository worldStructureRepository;

    @Autowired
    RandomLocation randomLocation;
    ObjectMapper om = new ObjectMapper();

    @Autowired
    Portal portal;



    public void mapToObject(String url) throws Exception {

        List<Creature> creatureList= new ArrayList<>();
        Map<String, Object> resultMap = om.readValue(new URL(url), new TypeReference<Map<String, Object>>() {
        });
        List<Feature> features = om.convertValue(resultMap.get("features"), new TypeReference<List<Feature>>() {
        });

        for (Feature f : features) {

            Map<String, Object> properties = f.getProperties();

            GeoJsonObject geometry = f.getGeometry();
            if (geometry instanceof Point) {
                Point p = (Point) geometry;

                portal.setLatitude(p.getCoordinates().getLatitude());
                portal.setLongitude(p.getCoordinates().getLongitude());
                System.out.println(portal.getLatitude()+" "+portal.getLongitude());
                for (int i = 0; i < 5 ; i++) {
                    Creature creature= new Creature("Warrior", Undead.getInstance(), Lemesur.getInstance(),false);
                    LocationModel locationModel;
                    locationModel=randomLocation.getRandomLocation(p.getCoordinates().getLongitude(),p.getCoordinates().getLatitude(),150);
                    creature.setLatitude(locationModel.getLatitude());
                    creature.setLongitude(locationModel.getLongitude());
                    creatureList.add(creature);
                }
                portal.setCreatureList(creatureList);
                worldStructureRepository.save(new WorldStructure(portal));

            } else if (geometry != null) {
                throw new RuntimeException("Unhandled geometry type: " + geometry.getClass().getName());
            }
        }
    }
}
