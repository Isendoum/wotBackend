package com.wot.wotbackend.itemModel.Abilities;

import com.wot.wotbackend.itemModel.ItemAbility;
import lombok.Data;

@Data

public class HealIp extends ItemAbility {

    private static volatile HealIp healIp = new HealIp();

    private HealIp(){
        this.setAbilityModifier(10);
        this.setAbilityDescription("Restore 10% of max ip.");

    }

    public static HealIp getInstance(){
        return healIp;
    }


}
