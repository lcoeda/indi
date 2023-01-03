package com.sparta.indi.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
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

    public User(String username, String pw, String email){
        this.username = username;
        this.pw = pw;
        this.email = email;
    }

}
