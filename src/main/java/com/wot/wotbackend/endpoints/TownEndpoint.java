package com.wot.wotbackend.endpoints;


import com.wot.wotbackend.Security.jwt.JwtUtils;
import com.wot.wotbackend.Security.services.PlayerDetailsImpl;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.documents.ERole;
import com.wot.wotbackend.documents.Player;
import com.wot.wotbackend.documents.Role;
import com.wot.wotbackend.helperClasses.payloads.securityPayloads.JwtResponse;
import com.wot.wotbackend.helperClasses.payloads.securityPayloads.LoginRequest;
import com.wot.wotbackend.helperClasses.payloads.securityPayloads.MessageResponse;
import com.wot.wotbackend.helperClasses.payloads.securityPayloads.SignupRequest;
import com.wot.wotbackend.helperClasses.payloads.shopPayloads.WrapperShopBuyObj;
import com.wot.wotbackend.helperClasses.payloads.shopPayloads.WrapperShopSellObj;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.questModel.Quest;
import com.wot.wotbackend.repositories.PlayerRepository;
import com.wot.wotbackend.repositories.RoleRepository;
import com.wot.wotbackend.worldStructures.portal.Shop;
import com.wot.wotbackend.worldStructures.portal.TownShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/rest/town")

public class TownEndpoint {


    @Autowired
    private PlayerRepository playerRepository;





    @GetMapping("/askTownShop")
    @ResponseBody
    public TownShop getTownShop() {

        return TownShop.getInstance();

    }

    @PostMapping(value="/townShop/buy",consumes = "application/json")
    @ResponseBody
    public Player buyItemFromTownShop(@RequestBody WrapperShopBuyObj wrapperShopBuyObj){

        TownShop.getInstance().getShopList().forEach(item -> {if(item.getId().equals(wrapperShopBuyObj.getItemId())){
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

        Player player= playerRepository.findById(wrapperShopBuyObj.getPlayer().getId()).get();
        return player;


    }

    @PostMapping("/townShop/sell")
    @ResponseBody
    public Player sellItemToTownShop(@RequestBody WrapperShopSellObj wrapperShopSellObj){

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

    @GetMapping("/{pathVariable}/askQuest")
    @ResponseBody
    public Player askForQuest(@PathVariable("pathVariable") String pathVariable) {
        System.out.println("Player asked for quest!");
        if(playerRepository.findById(pathVariable).isPresent()){
            Quest quest= new Quest();
            int random = randomWithRange(1,2);
            switch (random){
                case 1:
                    quest.generateTravelQuest();
                    break;
                case 2:
                    quest.generateKillQuest();
            }
            Player player= playerRepository.findById(pathVariable).get();
            player.getPlayerCharacter().getQuestList().add(quest);
            playerRepository.save(player);
            return player;
        }else{
            return null;
        }
    }



    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}
