package com.wot.wotbackend.endpoints;


import com.wot.wotbackend.documents.Player;
import com.wot.wotbackend.helperClasses.payloads.shopPayloads.WrapperShopBuyObj;
import com.wot.wotbackend.helperClasses.payloads.shopPayloads.WrapperShopSellObj;
import com.wot.wotbackend.questModel.Quest;
import com.wot.wotbackend.repositories.PlayerRepository;
import com.wot.wotbackend.worldStructures.town.TownShop;
import com.wot.wotbackend.worldStructures.town.TownSkillShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicBoolean;


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

    @GetMapping("/askTownSkillShop")
    @ResponseBody
    public TownSkillShop getTownSkillShop(){

        return TownSkillShop.getInstance();
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

    @PostMapping(value="/townSkillShop/buySkill/{playerId}/{skillName}")
    @ResponseBody
    public Player buySkillFromTownSkillShop(@PathVariable("playerId")String playerId,@PathVariable("skillName")String skillName){
        AtomicBoolean isSkillBought= new AtomicBoolean(false);
        playerRepository.findById(playerId).ifPresent(player -> {

            if(player.getPlayerCharacter().getCharSkillByName(skillName)!=null){

            }else {
                for (int i = 0; i < TownSkillShop.getInstance().getSkills().size(); i++) {
                    if(TownSkillShop.getInstance().getSkills().get(i).getCharacterSkillName().equals(skillName) && TownSkillShop.getInstance().getSkills().get(i).getCrystalCost()<=player.getPlayerCharacter().getSkillPoints()){
                        player.getPlayerCharacter().getCharacterSkills().add(TownSkillShop.getInstance().getSkills().get(i));
                        int skillPointsTemp=0;
                        skillPointsTemp=player.getPlayerCharacter().getSkillPoints();
                        player.getPlayerCharacter().setSkillPoints(skillPointsTemp-TownSkillShop.getInstance().getSkills().get(i).getCrystalCost());
                        playerRepository.save(player);
                        isSkillBought.set(true);
                    }
                }

            }
        });

        if(isSkillBought.get()){
            return playerRepository.findById(playerId).get();
            }
        else {
            return null;
        }


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
