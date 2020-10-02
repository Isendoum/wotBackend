package com.wot.wotbackend.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.creatureModel.Creature;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Timer;

@Document
@Data
@JsonSerialize
@JsonDeserialize
@NoArgsConstructor
public class Battle {
    @Id
    private String id;
    private Player player;
    private Creature creature;
    @JsonIgnore
    private static Timer timer;




    public Battle(Player player,Creature creature){
        this.player=player;
        this.creature=creature;
    }

    public void playerAttack(){
        this.creature.setHp(this.creature.getHp()-this.player.getPlayerCharacterList().get(0).getAttack());
    }
    public void playerAttackMove(CharacterSkill skill){
        String skillType= skill.getCharacterSkillType();
        switch (skillType)
        {
            case "Melee Attack":
                int playerAttackWithSkillModifier= Math.round(this.player.getPlayerCharacterList().get(0).getAttack()*skill.getCharacterSkillModifier());
                int hitTaken = Math.round(playerAttackWithSkillModifier-(this.creature.getDefence()*0.1f));
                System.out.println("Hit dmged for: " +hitTaken);
                this.creature.setHp(this.creature.getHp()-hitTaken);
            break;
            case "Magic Attack":
                int playerMagicAttackWithSkillModifier = Math.round(this.player.getPlayerCharacterList().get(0).getMagicAttack()*skill.getCharacterSkillModifier());
                int magicHitTaken = Math.round(playerMagicAttackWithSkillModifier-(this.creature.getMagicDefence()*0.1f));
                this.creature.setHp(this.creature.getHp()-magicHitTaken);
                break;
        }

    }

    @Scheduled(initialDelay = 1000,fixedRate = 5000)
    public void creatureAttack(){
        int hitTaken = Math.round(((100 / this.player.getPlayerCharacterList().get(0).getDefence())/10)*this.creature.getAttack());

            this.player.getPlayerCharacterList().get(0).reduceCurrentHp(hitTaken);

        this.player.getPlayerCharacterList().get(0).reduceCurrentHp(this.creature.getAttack());
    }

}
