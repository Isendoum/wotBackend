package com.wot.wotbackend.endpoints;

import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.documents.Battle;
import com.wot.wotbackend.documents.Player;
import com.wot.wotbackend.helperClasses.WrapperBattleObj;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.repositories.BattleRepository;
import com.wot.wotbackend.repositories.PlayerRepository;
import com.wot.wotbackend.services.location.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.Optional;

@RestController
@RequestMapping("/rest/battle")
@CrossOrigin("*")
public class BattleEndpoint {

    @Autowired
    BattleRepository battleRepository;

    @Autowired
    DistanceCalculator distanceCalculator;

    @Autowired
    PlayerRepository playerRepository;

    @PostMapping(value = "/init",consumes = "application/json")
    @ResponseBody
    public Battle initiateBattle(@RequestBody WrapperBattleObj wrapperBattleObj){
        System.out.println("--------\nCreature id: "+wrapperBattleObj.getCreature().getId()+"\nLatitude: "+wrapperBattleObj.getPlayer().getLatitude()+" Longitude: "+wrapperBattleObj.getPlayer().getLongitude() +
                "\nLatitude: "+ wrapperBattleObj.getCreature().getLatitude()+" Longitude"+wrapperBattleObj.getCreature().getLongitude());
        System.out.println(distanceCalculator.distance(wrapperBattleObj.getPlayer().getLatitude(),wrapperBattleObj.getPlayer().getLongitude(),wrapperBattleObj.getCreature().getLatitude(),wrapperBattleObj.getCreature().getLongitude(),"K"));
        if(distanceCalculator.distance(wrapperBattleObj.getPlayer().getLatitude(),wrapperBattleObj.getPlayer().getLongitude(),wrapperBattleObj.getCreature().getLatitude(),wrapperBattleObj.getCreature().getLongitude(),"K")<=0.15 && wrapperBattleObj.getPlayer().getPlayerCharacter().getCurrentHp()>0){
            Player player = playerRepository.findById(wrapperBattleObj.getPlayer().getId()).get();

            //sets creature level based on character level
            wrapperBattleObj.getCreature().setLevel(wrapperBattleObj.getPlayer().getPlayerCharacter().getLevel());
            //create random item and assign it to creature item list
            Item itemToAdd= Item.createRandomItem(wrapperBattleObj.getPlayer().getPlayerCharacter().getLevel());
            System.out.println("Created item level: " + itemToAdd.getLevelRequired());
            wrapperBattleObj.getCreature().getItems().add(itemToAdd);
            wrapperBattleObj.getCreature().recalculateStats();
            Battle battle= new Battle(player,wrapperBattleObj.getCreature());
            battleRepository.save(battle);
            System.out.println("Battle with id: "+battle.getId()+" started");

            return battle;
        }else {
            System.out.println("Didn't create battle\n---------");
            return null;
        }
    }



    @DeleteMapping(value = "/{id}/flee")
    @ResponseBody
    public String fleeBattle(@PathVariable("id") String id) {
        System.out.println("I am in flee");
        Optional<Battle> res = this.battleRepository.findById(id);
        res.ifPresent(battle -> {
            playerRepository.save(battle.getPlayer());
            battleRepository.deleteById(id);
            System.out.println("battle deleted");
        });
        return res.map(battle -> "200").orElse("400");
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Battle getBattle(@PathVariable("id") String id){
        if(battleRepository.findById(id).isPresent()){
            System.out.println("battle update asked");
            Battle battle= battleRepository.findById(id).get();
            battle.creatureAttack();
            battleRepository.save(battle);
            return battleRepository.findById(id).get();
        } else return null;

    }

    @GetMapping("/{id}/creatureAttack")
    @ResponseBody
    public Battle creatureAttack(@PathVariable("id") String id){
        if(battleRepository.findById(id).isPresent()){

            Optional<Battle> battle = battleRepository.findById(id);
            battle.get().creatureAttack();
            if(battle.get().getPlayer().getPlayerCharacter().getCurrentHp()>0){
                battleRepository.save(battle.get());
                return battleRepository.findById(id).get();
            }else{
                battle.get().getPlayer().getPlayerCharacter().setCurrentHp(0);
                playerRepository.save(battle.get().getPlayer());
                battleRepository.deleteById(id);
                return battle.get();
            }

        } else{
            return null;
        }

    }

    @PostMapping("/{id}/attack")
    @ResponseBody
    public Battle playerAttack(@PathVariable("id") String id, @RequestBody CharacterSkill skill){
        System.out.println("Player used"+skill.getCharacterSkillName());
            if(battleRepository.findById(id).isPresent()) {
                Optional<Battle> battle = battleRepository.findById(id);

                battle.get().playerAttackMove(skill);
                if(battle.get().getCreature().getHp()>0){
                    battleRepository.save(battle.get());
                    return battleRepository.findById(id).get();
                }
                else{
                    battle.get().getCreature().setHp(0);
                    long playerCurrentExp = battle.get().getPlayer().getPlayerCharacter().getExp();
                    int playerGold= battle.get().getPlayer().getPlayerCharacter().getGold();
                    int creatureExp = battle.get().getCreature().getExp();
                    int creatureGold= battle.get().getCreature().getGold();
                    battle.get().getPlayer().getPlayerCharacter().setExp(playerCurrentExp+creatureExp);
                    battle.get().getPlayer().getPlayerCharacter().setGold(playerGold+creatureGold);
                    battle.get().getPlayer().getPlayerCharacter().checkForLevelUp();

                    playerRepository.save(battle.get().getPlayer());
                    battleRepository.deleteById(id);
                    return battle.get();
                }

            } else{
                return null;
            }


        }


}
