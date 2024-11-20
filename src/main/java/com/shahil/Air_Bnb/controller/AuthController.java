package com.shahil.Air_Bnb.controller;


import com.shahil.Air_Bnb.entity.AppUser;
import com.shahil.Air_Bnb.exception.UserExists;
import com.shahil.Air_Bnb.payload.AppUserDto;
import com.shahil.Air_Bnb.payload.JWTToken;
import com.shahil.Air_Bnb.payload.LoginDto;
import com.shahil.Air_Bnb.repository.AppUserRepository;
import com.shahil.Air_Bnb.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AppUserService appUserService;
    private AppUserRepository appUserRepository;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;

    }

    @PostMapping("/createUser")
    public ResponseEntity<AppUserDto> createUser(@RequestBody AppUserDto dto) {



        AppUserDto appUser = appUserService.createUser(dto);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(
            @RequestBody LoginDto loginDto
    ){
       String token=appUserService.verifyLogin(loginDto);
       JWTToken jwtToken=new JWTToken();
       if (token!=null){
           jwtToken.setTokenType("JWT");
           jwtToken.setToken(token);
           return new ResponseEntity<>(jwtToken,HttpStatus.OK);
       }else {
           return new ResponseEntity<>("Invalid username/password",HttpStatus.UNAUTHORIZED);
       }
    }
}
