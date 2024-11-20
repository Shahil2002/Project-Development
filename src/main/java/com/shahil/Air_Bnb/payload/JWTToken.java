package com.shahil.Air_Bnb.payload;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTToken {

    private  String tokenType;
    private String token;

}
