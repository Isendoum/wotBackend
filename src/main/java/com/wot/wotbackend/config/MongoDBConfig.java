package com.wot.wotbackend.config;



import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.creatureModel.creatureClan.Lemesur;
import com.wot.wotbackend.creatureModel.types.Undead;
import com.wot.wotbackend.repositories.PlayerRepository;


import com.wot.wotbackend.repositories.RoleRepository;
import com.wot.wotbackend.repositories.WorldStructureRepository;
import com.wot.wotbackend.services.location.LocationModel;
import com.wot.wotbackend.services.location.RandomLocation;
import com.wot.wotbackend.worldStructures.portal.Portal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;


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

    @Bean
    CommandLineRunner commandLineRunner(){
        double latitude=37.4219983;
        double longitude=-122.084;
        Portal portal= new Portal();
        portal.setLatitude(latitude);
        portal.setLongitude(longitude);



        List<Creature> creatureList= new ArrayList<>();

        for (int i = 0; i < 5 ; i++) {
            Creature creature= new Creature("Warrior", Undead.getInstance(), Lemesur.getInstance());
            LocationModel locationModel;
            locationModel=randomLocation.getRandomLocation(longitude,latitude,100);
            creature.setLatitude(locationModel.getLatitude());
            creature.setLongitude(locationModel.getLongitude());
            creatureList.add(creature);

        }

        portal.setCreatureList(creatureList);
        return strings->{
            /*Player player = new Player("CptIsendoum","test",new Character("Isendoum", HumanRace.getInstance()));
            player.getPlayerCharacter().addItemToInventory(new Potion());
            player.setLatitude(latitude);
            player.setLongitude(longitude);
            player.getPlayerCharacter().getCharacterSkills().add(MeleeAttack.getInstance());
            player.getPlayerCharacter().getCharacterSkills().add(MagicAttack.getInstance());
            player.getPlayerCharacter().getCharacterSkills().add(WildSwing.getInstance());
            player.getPlayerCharacter().getCharacterSkills().add(ArcaneBolt.getInstance());

            playerRepository.save(player);
            Item item = new Potion();
            System.out.println(item.getItemName());
            item.increaseQuantity();*/



            /*playerRepository.findById("5f858e73beb3d35546a70ecb").ifPresent(player->
                    {


                        player.getPlayerCharacter().getCharacterSkills().add(MeleeAttack.getInstance());
                        player.getPlayerCharacter().getCharacterSkills().add(MagicAttack.getInstance());
                        player.getPlayerCharacter().getCharacterSkills().add(WildSwing.getInstance());
                        player.getPlayerCharacter().getCharacterSkills().add(ArcaneBolt.getInstance());


                    playerRepository.save(player);
                    }
            );*/
           // Shop shop= new Shop(10);
            //shop.setLatitude(latitude);
            //shop.setLongitude(longitude);
            //shop.addItemsToShop();
         // worldStructureRepository.save(new WorldStructure(portal));

        };



    }


}
