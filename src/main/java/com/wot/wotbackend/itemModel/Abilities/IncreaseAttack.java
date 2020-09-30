package com.wot.wotbackend.itemModel.Abilities;

import com.wot.wotbackend.itemModel.ItemAbility;

public class IncreaseAttack extends ItemAbility {

    private static volatile IncreaseAttack increaseAttack = new IncreaseAttack();

    private IncreaseAttack(){
        this.setAbilityModifier(70);
        this.setAbilityDescription("Increase attack: +70");

    }

    public static IncreaseAttack getInstance(){
        return increaseAttack;
    }

}
