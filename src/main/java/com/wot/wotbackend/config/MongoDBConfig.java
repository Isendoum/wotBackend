package com.wot.wotbackend.config;



import com.wot.wotbackend.characterModel.Character;
import com.wot.wotbackend.characterModel.characterClass.WarriorClass;
import com.wot.wotbackend.characterModel.characterRace.HumanRace;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.creatureModel.creatureClan.Lemesur;
import com.wot.wotbackend.creatureModel.types.Undead;
import com.wot.wotbackend.documents.Player;
import com.wot.wotbackend.itemModel.GearModels.Weapon;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.itemModel.Items.Potion;
import com.wot.wotbackend.repositories.PlayerRepository;


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
            Player player = new Player("CptIsendoum",new Character("Isendoum", HumanRace.getInstance(), WarriorClass.getInstance()));
            player.getPlayerCharacterList().get(0).addItemToInventory(new Potion());
            player.setLatitude(latitude);
            player.setLongitude(longitude);

            //playerRepository.save(player);
            Item item = new Potion();
            System.out.println(item.getItemName());
            item.increaseQuantity();
            Weapon weapon= new Weapon();
            weapon.setLevelRequired(1);
            weapon.setItemName("Rusty Sword");
            weapon.setItemType("Weapon");
            int rand= (int)(Math.random()*10)+1;
            weapon.setAttackModifier(rand*weapon.getLevelRequired());
            playerRepository.findById("5f73b1412d346c7c49bc626e").ifPresent(x->
                    {



                        //x.getPlayerCharacterList().get(0).addItemToInventory(weapon);
                        for (int i = 0; i < 5 ; i++) {
                            x.getPlayerCharacterList().get(0).addItemToInventory(item);
                        }

                    playerRepository.save(x);
                    }
            );
            //worldStructureRepository.save(new WorldStructure(portal));

        };



    }


}
