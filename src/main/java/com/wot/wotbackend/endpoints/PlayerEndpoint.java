package com.wot.wotbackend.endpoints;


import com.wot.wotbackend.documents.Player;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/player")
public class PlayerEndpoint {


    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/all")
    public List<Player> getAll(){
        return playerRepository.findAll();
    }

    @RequestMapping("/{pathVariable}")
    @ResponseBody
    public Player player(@PathVariable("pathVariable") String pathVariable) {
        System.out.println("Player asked!");
        if(playerRepository.findById(pathVariable).isPresent()){
            return playerRepository.findById(pathVariable).get();
        }else {
            return null;
        }

    }

    @PostMapping("/{pathVariable}/updateLastLocation")
    public void updateLastPlayerCoords(@PathVariable("pathVariable") String pathVariable,@RequestParam String latitude,@RequestParam String longitude) {
        System.out.println(latitude+" "+longitude);
        if(playerRepository.findById(pathVariable).isPresent()){
            Player player= playerRepository.findById(pathVariable).get();
           player.setLatitude(Double.parseDouble(latitude));
           player.setLongitude(Double.parseDouble(longitude));
           playerRepository.save(player);
            System.out.println("player location saved");
        }

    }



    @PostMapping("/{pathVariable}/useItem")
    @ResponseBody
    public Player useItemFromInventory(@PathVariable("pathVariable") String pathVariable,@RequestBody Item item) {
        System.out.println("Player asked to use an item with name "+item.getItemName());
        playerRepository.findById(pathVariable).ifPresent(player -> {
            System.out.println("Player "+player.getName()+item.getItemName());

            player.getPlayerCharacterList().get(0).useItemFromInventoryById(item.getId());
            player.getPlayerCharacterList().get(0).recalculateStats();
            playerRepository.save(player);
        });
        return playerRepository.findById(pathVariable).get();
    }

    @PostMapping("/{pathVariable}/removeItem")
    @ResponseBody
    public Player removeItemFromInventory(@PathVariable("pathVariable") String pathVariable,@RequestBody Item item) {
        System.out.println("Player asked to use an item with name "+item.getItemName());
        playerRepository.findById(pathVariable).ifPresent(player -> {
            System.out.println("Player "+player.getName()+item.getItemName());
            player.getPlayerCharacterList().get(0).removeItemFromInventoryById(item.getId());
            playerRepository.save(player);
        });
        return playerRepository.findById(pathVariable).get();
    }
    @PostMapping("/{pathVariable}/addItem")
    @ResponseBody
    public void  addItemToInventory(@PathVariable("pathVariable") String pathVariable,@RequestBody Item item) {
        //System.out.println("Player asked to use an item with name "+item.getItemName());
        playerRepository.findById(pathVariable).ifPresent(player -> {
            //System.out.println("Player "+player.getName()+item.getItemName());
            player.getPlayerCharacterList().get(0).addItemToInventory(item);
            playerRepository.save(player);
        });

    }



}
