package com.wot.wotbackend.characterModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.wot.wotbackend.characterModel.characterClass.CharacterMainClass;
import com.wot.wotbackend.characterModel.characterRace.CharacterRace;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.itemModel.Abilities.HealHp;
import com.wot.wotbackend.itemModel.Gear;
import com.wot.wotbackend.itemModel.GearModels.*;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.itemModel.Items.Potion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize
/*CharacterModel abstract class is used as the blueprint for creating new Player characters*/
public abstract class CharacterModel {
    private String name;
    private float maxHp;
    private float currentHp;
    private float defence;
    private float attack;
    private float magicAttack;
    private float magicDefence;
    private float speed;
    private int level;
    private long exp;
    private int maxInnerPower;
    private int currentInnerPower;
    private int talentPoints;
    private int levelPoints;
    private Gear gear;
    private String resourceName="Inner Power";
    private List<CharacterSkill> characterSkills;
    private CharacterSkill skill1;
    private CharacterSkill skill2;
    private CharacterSkill skill3;
    private CharacterSkill skill4;
    private CharacterRace characterRace;
    private int gold;
    private List<Item> inventory = new ArrayList<>();




    public void initGear(){
        this.gear= new Gear();
    }

    public void reduceCurrentHp(float hp){
        this.currentHp=this.currentHp-hp;
    }

    public void increaseCurrentHp(float hp){this.currentHp=this.currentHp+hp;}

    //initialize base Hp from character race
    public void initMaxHpAndCurrentHp(){
        if(this.characterRace!=null){
            this.maxHp = this.characterRace.getStatModifier()*this.level;
            this.maxHp = this.maxHp
                    +this.getGear().getWeapon().getHpModifier()
                    +this.getGear().getOffHand().getHpModifier()
                    +this.getGear().getPants().getHpModifier()
                    +this.getGear().getChest().getHpModifier()
                    +this.getGear().getHelmet().getHpModifier()
                    +this.getGear().getBoots().getHpModifier()
                    +this.getGear().getRing1().getHpModifier()
                    +this.getGear().getRing2().getHpModifier()
                    +this.getGear().getAmulet().getHpModifier()
                    +this.getGear().getShoulders().getHpModifier()
                    +this.getGear().getGloves().getHpModifier();
        }
        this.currentHp=this.maxHp;
    }

    public void initMaxHp(){
        if(this.characterRace!=null){
            this.maxHp = (this.characterRace.getStatModifier())*this.level;
            this.maxHp = this.maxHp
                    +this.getGear().getWeapon().getHpModifier()
                    +this.getGear().getOffHand().getHpModifier()
                    +this.getGear().getPants().getHpModifier()
                    +this.getGear().getChest().getHpModifier()
                    +this.getGear().getHelmet().getHpModifier()
                    +this.getGear().getBoots().getHpModifier()
                    +this.getGear().getRing1().getHpModifier()
                    +this.getGear().getRing2().getHpModifier()
                    +this.getGear().getAmulet().getHpModifier()
                    +this.getGear().getShoulders().getHpModifier()
                    +this.getGear().getGloves().getHpModifier();
        }
        if(this.maxHp<this.currentHp){
            this.setCurrentHp(this.maxHp);
        }

    }

    //initialize base Inner Power from character race
    public void initMaxInnerPowerAndCurrentInnerPower(){
        if(this.characterRace!=null){
            this.maxInnerPower = Math.round(this.characterRace.getStatModifier()*this.level);
            this.maxInnerPower = this.maxInnerPower+this.getGear().getHelmet().getMagicAttackModifier()
                    +this.getGear().getShoulders().getMagicAttackModifier()
                    +this.getGear().getChest().getMagicAttackModifier()
                    +this.getGear().getGloves().getMagicAttackModifier()
                    +this.getGear().getBoots().getMagicAttackModifier()
                    +this.getGear().getPants().getMagicAttackModifier();
        }
        this.currentInnerPower=this.maxInnerPower;
    }

    public void initMaxInnerPower(){
        if(this.characterRace!=null){
            this.maxInnerPower = Math.round(this.characterRace.getStatModifier()*this.level);
            this.maxInnerPower = this.maxInnerPower+this.getGear().getHelmet().getMagicAttackModifier()
                    +this.getGear().getShoulders().getMagicAttackModifier()
                    +this.getGear().getChest().getMagicAttackModifier()
                    +this.getGear().getGloves().getMagicAttackModifier()
                    +this.getGear().getBoots().getMagicAttackModifier()
                    +this.getGear().getPants().getMagicAttackModifier();
        }
        if(this.maxInnerPower<this.currentInnerPower){
            this.setCurrentInnerPower(this.maxInnerPower);
        }
    }

    //Adds an item object to inventory
    public void addItemToInventory(Item itemToAdd) {
        System.out.println("Item inside add" + itemToAdd);
        switch (itemToAdd.getItemType()) {
            case "Weapon":
            case "Gloves":
            case "Boots":
            case "Amulet":
            case "Ring":
            case "Shoulders":
            case "Pants":
            case "Chest":
            case "Helmet":
            case "Off-Hand":
                if (this.inventory.size() < 20) {

                    this.inventory.add(itemToAdd);
                }
                break;
            case "Consumable":

                boolean itemFound = false;
                if (this.inventory.size() == 0) {
                    this.inventory.add(itemToAdd);
                    break;
                } else {
                    for (int i = 0; i < this.inventory.size(); i++) {

                        if (this.inventory.get(i).getItemName().equals(itemToAdd.getItemName())) {
                            this.inventory.get(i).increaseQuantity();
                            itemFound = true;
                            break;
                        }

                    }
                    if (!itemFound) {
                        this.inventory.add(itemToAdd);
                        break;
                    }
                }
                break;
        }
    }

    //removes an item object from the inventory by id
    public void removeItemFromInventoryById(String id){
        for (int i = 0; i < this.inventory.size() ; i++) {
            if(this.inventory.get(i).getId().equals(id)){
                this.inventory.remove(this.inventory.get(i));
                break;
            }
        }
    }
    //uses an item from inventory by its id
    public void useItemFromInventoryById(String id){

        for (int i = 0; i < this.getInventory().size(); i++) {

            if(this.getInventory().get(i).getId().equals(id)){

                if(this.getInventory().get(i).getItemType().equals("Consumable")){
                    if(this.getInventory().get(i).getItemName().equals("Potion")){

                        int hpToHeal=Math.round(this.getMaxHp()*(this.getInventory().get(i).getItemAbility().getAbilityModifier()/100));

                        if(this.getCurrentHp()==this.getMaxHp()){
                            break;
                        }else if(this.getCurrentHp()+hpToHeal>=this.getMaxHp()){
                            this.setCurrentHp(this.getMaxHp());
                            this.getInventory().get(i).decreaseQuantity();
                            if(this.getInventory().get(i).getQuantity()==0){
                                this.removeItemFromInventoryById(this.getInventory().get(i).getId());
                            }
                            break;

                        }else{
                            this.getInventory().get(i).decreaseQuantity();
                            this.increaseCurrentHp(hpToHeal);
                            if(this.getInventory().get(i).getQuantity()==0){
                                this.removeItemFromInventoryById(this.getInventory().get(i).getId());
                            }
                            break;

                        }
                    }
                }
                else if(this.getInventory().get(i).getItemType().equals("Weapon")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getWeapon().getLevelRequired()==0){
                            this.getGear().setWeapon(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }else{
                            this.addItemToInventory(this.getGear().getWeapon());
                            this.getGear().setWeapon(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }else if(this.getInventory().get(i).getItemType().equals("Boots")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getBoots().getLevelRequired()==0){
                            this.getGear().setBoots( this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }else{
                            this.addItemToInventory(this.getGear().getBoots());
                            this.getGear().setBoots( this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }else if(this.getInventory().get(i).getItemType().equals("Pants")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getPants().getLevelRequired()==0){
                            this.getGear().setPants(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }else{
                            this.addItemToInventory(this.getGear().getPants());
                            this.getGear().setPants( this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }else if(this.getInventory().get(i).getItemType().equals("Chest")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getChest().getLevelRequired()==0){
                            this.getGear().setChest(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }else{
                            this.addItemToInventory(this.getGear().getChest());
                            this.getGear().setChest( this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }
                else if(this.getInventory().get(i).getItemType().equals("Shoulders")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getShoulders().getLevelRequired()==0){
                            this.getGear().setShoulders( this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }else{
                            this.addItemToInventory(this.getGear().getShoulders());
                            this.getGear().setShoulders( this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }
                else if(this.getInventory().get(i).getItemType().equals("Gloves")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getGloves().getLevelRequired()==0){
                            this.getGear().setGloves(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }else{
                            this.addItemToInventory(this.getGear().getGloves());
                            this.getGear().setGloves(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }else if(this.getInventory().get(i).getItemType().equals("Helmet")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getHelmet().getLevelRequired()==0){
                            this.getGear().setHelmet(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }else{
                            this.addItemToInventory(this.getGear().getHelmet());
                            this.getGear().setHelmet(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }
                else if(this.getInventory().get(i).getItemType().equals("Amulet")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getAmulet().getLevelRequired()==0){
                            this.getGear().setAmulet( this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }else{
                            this.addItemToInventory(this.getGear().getAmulet());
                            this.getGear().setAmulet( this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }
                else if(this.getInventory().get(i).getItemType().equals("Ring")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getRing1().getLevelRequired()==0){
                            this.getGear().setRing1(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        } else if(this.getGear().getRing2().getLevelRequired()==0){
                            this.getGear().setRing2(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;
                        }
                        else{
                            if(this.getInventory().get(i).getMagicDefenceModifier()>this.getGear().getRing1().getMagicDefenceModifier()){
                                this.addItemToInventory(this.getGear().getRing1());
                                this.getGear().setRing1( this.getInventory().get(i));
                                this.getInventory().remove(i);
                                break;
                            }else if(this.getInventory().get(i).getMagicDefenceModifier()>this.getGear().getRing2().getMagicDefenceModifier()) {
                                this.addItemToInventory(this.getGear().getRing2());
                                this.getGear().setRing2( this.getInventory().get(i));
                                this.getInventory().remove(i);
                                break;
                            }else{
                                break;
                            }

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }else if(this.getInventory().get(i).getItemType().equals("Off-Hand")){

                    if(this.getInventory().get(i).getLevelRequired()<= this.getLevel()){

                        if(this.getGear().getOffHand().getLevelRequired()==0){
                            this.getGear().setOffHand(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }else{
                            this.addItemToInventory(this.getGear().getOffHand());
                            this.getGear().setOffHand(this.getInventory().get(i));
                            this.getInventory().remove(i);
                            break;

                        }
                    }else{
                        System.out.println("too low level");
                    }
                }

            }

        }

    }

    //initialize base Defence value from character race
    public void initDefence() {

        if(this.characterRace!=null){
            this.defence = (this.characterRace.getStatModifier())*this.level;

                this.defence= this.defence
                    +this.getGear().getWeapon().getDefenceModifier()
                    +this.getGear().getOffHand().getDefenceModifier()
                    +this.getGear().getPants().getDefenceModifier()
                    +this.getGear().getChest().getDefenceModifier()
                    +this.getGear().getHelmet().getDefenceModifier()
                    +this.getGear().getBoots().getDefenceModifier()
                    +this.getGear().getRing1().getDefenceModifier()
                    +this.getGear().getRing2().getDefenceModifier()
                    +this.getGear().getAmulet().getDefenceModifier()
                    +this.getGear().getShoulders().getDefenceModifier()
                    +this.getGear().getGloves().getDefenceModifier();

        }
    }

    //initialize base Attack value from character race
    public void initAttack() {

        if(this.characterRace!=null){
            this.attack = ((this.characterRace.getStatModifier())*this.level);
            if(this.gear.getWeapon()!=null){
                this.attack= this.attack
                        +this.getGear().getWeapon().getAttackModifier()
                        +this.getGear().getOffHand().getAttackModifier()
                        +this.getGear().getPants().getAttackModifier()
                        +this.getGear().getChest().getAttackModifier()
                        +this.getGear().getHelmet().getAttackModifier()
                        +this.getGear().getBoots().getAttackModifier()
                        +this.getGear().getRing1().getAttackModifier()
                        +this.getGear().getRing2().getAttackModifier()
                        +this.getGear().getAmulet().getAttackModifier()
                        +this.getGear().getShoulders().getAttackModifier()
                        +this.getGear().getGloves().getAttackModifier();
            }
        }

    }

    //initialize base Magic Attack value from character race
    public void initMagicAttack() {

        if(this.characterRace!=null){
            this.magicAttack = (this.characterRace.getStatModifier())*this.level;
            this.magicAttack = this.magicAttack
                    +this.getGear().getWeapon().getMagicAttackModifier()
                    +this.getGear().getOffHand().getMagicAttackModifier()
                    +this.getGear().getPants().getMagicAttackModifier()
                    +this.getGear().getChest().getMagicAttackModifier()
                    +this.getGear().getHelmet().getMagicAttackModifier()
                    +this.getGear().getBoots().getMagicAttackModifier()
                    +this.getGear().getRing1().getMagicAttackModifier()
                    +this.getGear().getRing2().getMagicAttackModifier()
                    +this.getGear().getAmulet().getMagicAttackModifier()
                    +this.getGear().getShoulders().getMagicAttackModifier()
                    +this.getGear().getGloves().getMagicAttackModifier();
        }
    }

    //initialize base Magic Defence value from character race and character class
    public void initMagicDefence() {

        if(this.characterRace!=null){
            this.magicDefence = (this.characterRace.getStatModifier())*this.level;
            this.magicDefence = this.magicDefence + +this.getGear().getWeapon().getMagicAttackModifier()
                    +this.getGear().getOffHand().getMagicDefenceModifier()
                    +this.getGear().getPants().getMagicDefenceModifier()
                    +this.getGear().getChest().getMagicDefenceModifier()
                    +this.getGear().getHelmet().getMagicDefenceModifier()
                    +this.getGear().getBoots().getMagicDefenceModifier()
                    +this.getGear().getRing1().getMagicDefenceModifier()
                    +this.getGear().getRing2().getMagicDefenceModifier()
                    +this.getGear().getAmulet().getMagicDefenceModifier()
                    +this.getGear().getShoulders().getMagicDefenceModifier()
                    +this.getGear().getGloves().getMagicDefenceModifier();
        }
    }

    public void initSpeed() {
        if(this.characterRace!=null){
            this.speed = (this.characterRace.getStatModifier())*this.level;
        }
    }

    //Runs all initializes
    public void statInitializer(){
        this.initGear();
        this.initMaxHpAndCurrentHp();
        this.initMaxInnerPowerAndCurrentInnerPower();
        this.initAttack();
        this.initDefence();
        this.initMagicAttack();
        this.initMagicDefence();
        this.initSpeed();
        this.characterSkills= new ArrayList<>();

    }

    //recalculates character stats when character level up (use for level up only!)
    public void recalculateStatsForLvl(){
        this.initMaxHpAndCurrentHp();
        this.initMaxInnerPowerAndCurrentInnerPower();
        this.initAttack();
        this.initDefence();
        this.initMagicAttack();
        this.initMagicDefence();
        this.initSpeed();
    }
    //recalculates character stats generally
    public void recalculateStats(){
        this.initMaxHp();
        this.initMaxInnerPower();
        this.initAttack();
        this.initDefence();
        this.initMagicAttack();
        this.initMagicDefence();
        this.initSpeed();
    }

    public CharacterSkill getCharSkillByName(String skillName){
        if(skillName.equals(characterSkills.listIterator().next().getCharacterSkillName())){
            return characterSkills.listIterator().next();
        }else return null;

    }

    //check if the character should level up, if character is eligible for level up increases level and recalculates character stats
    public void checkForLevelUp(){
        Double expNeeded=100* Math.pow(this.getLevel(),2.5);
        if(this.exp>expNeeded.longValue()){
            this.setLevel(this.getLevel()+1);
            recalculateStatsForLvl();
            System.out.println("level up!");
        }
    }




}
