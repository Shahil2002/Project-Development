package com.shahil.Air_Bnb.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class AppUserDto {

    private long id;
    private String name;
    private String username;
    private String email;

    private String password;

}
