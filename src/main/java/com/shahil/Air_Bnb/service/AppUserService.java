package com.shahil.Air_Bnb.service;

import com.shahil.Air_Bnb.entity.AppUser;
import com.shahil.Air_Bnb.exception.UserExists;
import com.shahil.Air_Bnb.payload.AppUserDto;
import com.shahil.Air_Bnb.payload.LoginDto;
import com.shahil.Air_Bnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {


    private AppUserRepository appUserRepository;
    private JWTService jwtService;


    public AppUserService(AppUserRepository appUserRepository, JWTService jwtService) {

        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    // Create a new user
    public AppUserDto createUser(AppUserDto dto) {
        AppUser user = dtoToEntity(dto);

        // Convert DTO to entity

        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            throw new UserExists("Email ID exists");
        }

        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            throw new UserExists("Username exists");
        }
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        AppUser savedUser = appUserRepository.save(user);
        return entityToDto(savedUser); // Convert the saved entity back to DTO
    }


    // entity to AppUserDto
    private AppUserDto entityToDto(AppUser entity) {
        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        // Exclude the password for security reasons

        return dto;
    }

    // AppUserDto to AppUser entity
    private AppUser dtoToEntity(AppUserDto dto) {
        AppUser entity = new AppUser();
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }


    public String verifyLogin(LoginDto loginDto) {
//        Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());
        Optional<AppUser> opUser = appUserRepository.findByEmailOrUsername(loginDto.getUsername(), loginDto.getUsername());
        if (opUser.isPresent()) {
            AppUser appUser = opUser.get();
            System.out.println(opUser);
            if (BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())) {
                return jwtService.generateToken(appUser);

            }

        }

        return null;


    }
}
