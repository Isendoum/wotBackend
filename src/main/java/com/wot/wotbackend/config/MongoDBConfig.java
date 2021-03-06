package com.wot.wotbackend.config;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.creatureModel.creatureClan.Lemesur;
import com.wot.wotbackend.creatureModel.types.Undead;
import com.wot.wotbackend.documents.WorldStructure;
import com.wot.wotbackend.itemModel.ConsumableModel.IpPotion;
import com.wot.wotbackend.itemModel.ConsumableModel.Potion;
import com.wot.wotbackend.repositories.PlayerRepository;


import com.wot.wotbackend.repositories.RoleRepository;
import com.wot.wotbackend.repositories.WorldStructureRepository;
import com.wot.wotbackend.services.location.LocationModel;
import com.wot.wotbackend.services.location.RandomLocation;
import com.wot.wotbackend.worldStructures.portal.Portal;
import com.wot.wotbackend.worldStructures.portal.Shop;
import org.geojson.Feature;
import org.geojson.GeoJsonObject;
import org.geojson.Point;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Configuration
@EnableMongoRepositories("com.wot.wotbackend.repositories")
@EnableScheduling
@EnableAsync
public class MongoDBConfig {


    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    WorldStructureRepository worldStructureRepository;

    @Autowired
    RandomLocation randomLocation;
    @Autowired
    RoleRepository roleRepository;
    ObjectMapper om = new ObjectMapper();

    Portal portal= new Portal();
    List<Creature> creatureList= new ArrayList<>();

    @Bean
    CommandLineRunner commandLineRunner(){


        return strings->{

            /*portal.setLatitude(35.4856428);
            portal.setLongitude(24.0653315);

            Creature creature= new Creature("Warrior", Undead.getInstance(), Lemesur.getInstance());
            creature.setLatitude(35.4856431);
            creature.setLongitude(24.065332);
            portal.getCreatureList().add(creature);*/
            /*Shop shop = new Shop();
            shop.addItemsToShop(new Potion());
            shop.addItemsToShop(new IpPotion());
            shop.setLatitude(37.421998);
            shop.setLongitude(-122.0842);
           worldStructureRepository.save(new WorldStructure(shop));*/
        };



    }

    public void insertPortalsToDbFromGeojson() throws IOException {

        Map<String, Object> resultMap = om.readValue(new URL("file:///E:/crete_cafe.geojson"), new TypeReference<Map<String, Object>>() {
        });
        List<Feature> features = om.convertValue(resultMap.get("features"), new TypeReference<List<Feature>>() {
        });

        for (Feature f : features) {
            creatureList.clear();

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


            } else if (geometry != null) {
                throw new RuntimeException("Unhandled geometry type: " + geometry.getClass().getName());
            }
            portal.setCreatureList(creatureList);
            worldStructureRepository.save(new WorldStructure(portal));
        }

    }


}
