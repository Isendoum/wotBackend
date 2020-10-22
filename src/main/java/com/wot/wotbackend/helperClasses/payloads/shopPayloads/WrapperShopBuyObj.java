package com.wot.wotbackend.helperClasses.payloads.shopPayloads;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.creatureModel.Creature;
import com.wot.wotbackend.documents.Player;
import com.wot.wotbackend.itemModel.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
public class WrapperShopBuyObj {

    private Player player;
    private String shopId;
    private String itemId;


}
