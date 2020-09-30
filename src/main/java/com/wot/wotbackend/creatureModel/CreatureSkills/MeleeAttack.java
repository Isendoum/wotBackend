package com.wot.wotbackend.creatureModel.CreatureSkills;


public class MeleeAttack extends CreatureSkill {

    private static MeleeAttack meleeAttack= new MeleeAttack();

    private MeleeAttack(){

        this.setSkillName("Melee Attack");
        this.setSkillModifier(1);
    }

    public static MeleeAttack getInstance(){
        return meleeAttack;
    }
}
