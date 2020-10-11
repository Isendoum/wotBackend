package com.wot.wotbackend.helperClasses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonDeserialize
@JsonSerialize
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {


    String username;
    String password;

}
