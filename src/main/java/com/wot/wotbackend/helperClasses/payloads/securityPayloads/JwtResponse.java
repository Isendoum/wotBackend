package com.wot.wotbackend.helperClasses.payloads.securityPayloads;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wot.wotbackend.characterModel.Character;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonSerialize
@JsonDeserialize
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private Double latitude;
    private Double longitude;
    private Character character;
    private List<String> roles;

    public JwtResponse(String accessToken, String id, String username, String email,Double latitude,Double longitude, Character character, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.character=character;
        this.latitude=latitude;
        this.longitude=longitude;
        this.roles = roles;
    }
}
