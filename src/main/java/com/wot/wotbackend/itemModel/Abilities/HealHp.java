package com.wot.wotbackend.itemModel.Abilities;

import com.wot.wotbackend.itemModel.ItemAbility;
import lombok.Data;

@Data

public class HealHp extends ItemAbility {

    private static volatile HealHp healHp = new HealHp();

    private HealHp(){
        this.setAbilityModifier(10);
        this.setAbilityDescription("Heals player for 10% of max hp.");

    }

    public static HealHp getInstance(){
        return healHp;
    }


}
