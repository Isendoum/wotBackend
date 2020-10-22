package com.wot.wotbackend.documents;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.characterModel.Character;
import com.wot.wotbackend.characterModel.characterRace.HumanRace;
import com.wot.wotbackend.characterModel.characterSkill.ArcaneBolt;
import com.wot.wotbackend.characterModel.characterSkill.MagicAttack;
import com.wot.wotbackend.characterModel.characterSkill.MeleeAttack;
import com.wot.wotbackend.characterModel.characterSkill.WildSwing;
import com.wot.wotbackend.helperClasses.playerClasses.Career;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize

public class Player {


    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @DBRef
    private Set<Role> roles = new HashSet<>();
    private Double latitude;
    private Double longitude;
    private Date lastDate;
    private Character playerCharacter;
    private Career career;


    public Player(String username,String password,String email){
        this.username =username;
        this.lastDate=new Date();
        this.email=email;
        this.password = password;
        this.career= new Career();
        this.playerCharacter= new Character(username, HumanRace.getInstance());
        this.playerCharacter.getCharacterSkills().add(MeleeAttack.getInstance());
        this.playerCharacter.getCharacterSkills().add(MagicAttack.getInstance());
        this.playerCharacter.getCharacterSkills().add(WildSwing.getInstance());
        this.playerCharacter.getCharacterSkills().add(ArcaneBolt.getInstance());
    }

}
