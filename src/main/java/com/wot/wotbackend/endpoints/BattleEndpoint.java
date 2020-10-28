package com.wot.wotbackend.endpoints;

import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.documents.Battle;
import com.wot.wotbackend.documents.Player;
import com.wot.wotbackend.helperClasses.battleClasses.BattleType;
import com.wot.wotbackend.helperClasses.payloads.battlePayloads.WrapperBattleObj;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.repositories.BattleRepository;
import com.wot.wotbackend.repositories.PlayerRepository;
import com.wot.wotbackend.services.location.DistanceCalculator;
import com.wot.wotbackend.valorTower.ValorTower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicReference<Player> player = new AtomicReference<>();
        playerRepository.findById(wrapperBattleObj.getPlayer().getId()).ifPresent(player::set);
        System.out.println(player.get().getPlayerCharacter().getCurrentHp());
        if(distanceCalculator.distance(wrapperBattleObj.getPlayer().getLatitude(),wrapperBattleObj.getPlayer().getLongitude(),wrapperBattleObj.getCreature().getLatitude(),wrapperBattleObj.getCreature().getLongitude(),"K")<=0.15
                && player.get().getPlayerCharacter().getCurrentHp()>0)
        {


            //sets creature level based on character level
            wrapperBattleObj.getCreature().setLevel(wrapperBattleObj.getPlayer().getPlayerCharacter().getLevel());
            //create random item and assign it to creature item list
            Item itemToAdd= Item.createRandomItem(wrapperBattleObj.getPlayer().getPlayerCharacter().getLevel());
            System.out.println("Created item level: " + itemToAdd.getLevelRequired());
            wrapperBattleObj.getCreature().getItems().add(itemToAdd);
            wrapperBattleObj.getCreature().recalculateStats();
            Battle battle= new Battle(player.get(),wrapperBattleObj.getCreature(), BattleType.WORLD);
            battleRepository.save(battle);
            System.out.println("Battle with id: "+battle.getId()+" started");

            return battle;
        }else {
            System.out.println("Didn't create battle\n---------");
            return null;
        }
    }

    @GetMapping(value = "/initTowerBattle/{id}")
    @ResponseBody
    public Battle initiateValorTowerBattle(@PathVariable("id") String id){
        if (playerRepository.findById(id).isPresent()) {
            if(playerRepository.findById(id).get().getPlayerCharacter().getCurrentHp()>0){
                Battle battle= new Battle();
                battle.setCreature(ValorTower.getInstance().getFloorList().get(playerRepository.findById(id).get().getCareer().getCurrentValorTowerFloor()));
                battle.setPlayer(playerRepository.findById(id).get());
                battle.setBattleTypeEnum(BattleType.VALORTOWER);
                battleRepository.save(battle);
                return battle;
            } else{
                return null;
            }
        } else{
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
                battle.get().getPlayer().getCareer().increasePlayerDeaths();
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
                    if(battle.get().getBattleTypeEnum().equals(BattleType.VALORTOWER)){
                        battle.get().getPlayer().getCareer().increaseCurrentValorFloor();
                    }
                    battle.get().getPlayer().getCareer().increaseCreatureKills();
                    battle.get().getPlayer().getPlayerCharacter().getQuestList().forEach(x->
                            x.checkObjectives(battle.get().getCreature())
                            );
                    playerRepository.save(battle.get().getPlayer());
                    battleRepository.deleteById(id);
                    return battle.get();
                }

            } else{
                return null;
            }


        }



}
