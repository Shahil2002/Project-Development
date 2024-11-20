package com.shahil.Air_Bnb.repository;

import com.shahil.Air_Bnb.entity.AppUser;
import com.shahil.Air_Bnb.payload.AppUserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmailOrUsername(String username,String email);
}