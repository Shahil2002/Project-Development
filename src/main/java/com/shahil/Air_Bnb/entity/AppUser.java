package com.shahil.Air_Bnb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "email", nullable = false, unique = true, length = 50)
        private String email;

        @Column(name = "username", nullable = false, unique = true, length = 50)
        private String username;

        @Column(name = "password", nullable = false)
        private String password;



}