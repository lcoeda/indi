package com.sparta.indi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false, unique = true)
    private String email;

    private long ph_number;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String pw, String email,long ph_number, UserRoleEnum role){
        this.username = username;
        this.pw = pw;
        this.email = email;
        this.ph_number = ph_number;
        this.role = role;
    }

}
