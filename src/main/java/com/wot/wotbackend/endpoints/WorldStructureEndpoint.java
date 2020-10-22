package com.wot.wotbackend.endpoints;

import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.documents.Player;
import com.wot.wotbackend.documents.WorldStructure;
import com.wot.wotbackend.helperClasses.payloads.shopPayloads.WrapperShopBuyObj;
import com.wot.wotbackend.helperClasses.payloads.shopPayloads.WrapperShopSellObj;
import com.wot.wotbackend.repositories.PlayerRepository;
import com.wot.wotbackend.repositories.WorldStructureRepository;
import com.wot.wotbackend.services.location.DistanceCalculator;
import com.wot.wotbackend.worldStructures.portal.Portal;
import com.wot.wotbackend.worldStructures.portal.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/rest/worldStructure")
public class WorldStructureEndpoint {

    @Autowired
    private WorldStructureRepository worldStructureRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private DistanceCalculator distanceCalculator;

    @GetMapping("/all")
    @ResponseBody
    public List<WorldStructure> getAll(){
        return worldStructureRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public List<WorldStructure> getNearby(@PathVariable("id") String id) {

       // System.out.println(id);
        List<WorldStructure> nearbyWorldStructures= new ArrayList<>();
        Optional<Player> player= playerRepository.findById(id);

        AtomicReference<Double> latitude = new AtomicReference<>((double) 0);
        AtomicReference<Double> longitude = new AtomicReference<>((double) 0);
        player.ifPresent(player1 -> {
            latitude.set(player1.getLatitude());
            longitude.set(player1.getLongitude());
        } );

        worldStructureRepository.findAll().forEach(worldStructure -> {
            if(distanceCalculator.distance(latitude.get(),longitude.get(),worldStructure.getStructureModel().getLatitude(),worldStructure.getStructureModel().getLongitude(),"K")<0.7){
                nearbyWorldStructures.add(worldStructure);
            }
        });


        return nearbyWorldStructures;
    }

    @GetMapping("/creatures/{id}")
    @ResponseBody
    public List<Creature> getNearbyCreatures(@PathVariable("id") String id) {

       // System.out.println("In get nearby creatures");
        List<WorldStructure> nearbyWorldStructures= new ArrayList<>();
        List<Creature> nearbyCreatures= new ArrayList<>();
        Optional<Player> player= playerRepository.findById(id);

        AtomicReference<Double> latitude = new AtomicReference<>((double) 0);
        AtomicReference<Double> longitude = new AtomicReference<>((double) 0);
        player.ifPresent(player1 -> {
            latitude.set(player1.getLatitude());
            longitude.set(player1.getLongitude());
        } );

        worldStructureRepository.findAll().forEach(worldStructure -> {
            if(distanceCalculator.distance(latitude.get(),longitude.get(),worldStructure.getStructureModel().getLatitude(),worldStructure.getStructureModel().getLongitude(),"K")<0.7) {
                //System.out.println("Got in from structure distance check");
                if (worldStructure.getStructureModel().getStructureType().equals("Portal")) {
                    Portal portal = (Portal) worldStructure.getStructureModel();
                    portal.getCreatureList().forEach(creature -> {
                        if (distanceCalculator.distance(latitude.get(), longitude.get(), creature.getLatitude(), creature.getLongitude(), "K") < 0.1) {
                            // System.out.println(" creature with id" +creature.getId());
                            nearbyCreatures.add(creature);
                        }
                    });
                }
            }
        });
        //System.out.println(nearbyWorldStructures.toString());

        return nearbyCreatures;
    }

    @PostMapping(value="/shop/buy",consumes = "application/json")
    @ResponseBody
    public Player buyItemFromShop(@RequestBody WrapperShopBuyObj wrapperShopBuyObj){
        System.out.println("Inside request");
        worldStructureRepository.findById(wrapperShopBuyObj.getShopId()).ifPresent(shop-> {
            System.out.println("shop found");
            Shop shopAsked =(Shop) shop.getStructureModel();
            shopAsked.getShopList().forEach(item -> {if(item.getId().equals(wrapperShopBuyObj.getItemId())){
                System.out.println("item found with id: " +item.getId());
                playerRepository.findById(wrapperShopBuyObj.getPlayer().getId()).ifPresent(player->{
                    System.out.println("Player found");
                    int itemWorth= item.getGoldValue();
                    int playerGold= player.getPlayerCharacter().getGold();
                    if(itemWorth<=playerGold){
                        System.out.println("item bought");
                        player.getPlayerCharacter().addItemToInventory(item);
                        player.getPlayerCharacter().setGold(playerGold-itemWorth);
                        playerRepository.save(player);
                    }
                });
            }
            });
        });
        Player player= playerRepository.findById(wrapperShopBuyObj.getPlayer().getId()).get();
        return player;


    }

    @PostMapping("/shop/sell")
    @ResponseBody
    public Player sellItemToShop(@RequestBody WrapperShopSellObj wrapperShopSellObj){

        playerRepository.findById(wrapperShopSellObj.getPlayer().getId()).ifPresent(player -> {
            player.getPlayerCharacter().removeItemFromInventoryById(wrapperShopSellObj.getItem().getId());
            int playerGold=player.getPlayerCharacter().getGold();
            int itemGold= wrapperShopSellObj.getItem().getGoldValue();
            player.getPlayerCharacter().setGold(playerGold+itemGold);
            playerRepository.save(player);
        });

        Player player= playerRepository.findById(wrapperShopSellObj.getPlayer().getId()).get();
        return player;

    }
}
