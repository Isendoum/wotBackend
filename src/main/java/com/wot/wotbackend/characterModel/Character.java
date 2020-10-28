package com.wot.wotbackend.characterModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wot.wotbackend.characterModel.characterClass.CharacterMainClass;
import com.wot.wotbackend.characterModel.characterRace.CharacterRace;
import com.wot.wotbackend.itemModel.Gear;
import com.wot.wotbackend.itemModel.Item;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonDeserialize
public class Character extends CharacterModel {

    public Character(String characterName, CharacterRace characterRace){
        this.setName(characterName);
        this.setCharacterRace(characterRace);
        this.setLevel(1);
        this.setExpRequired(Math.round(100* Math.pow(this.getLevel(),2.5)));
        this.statInitializer();

    }




}
