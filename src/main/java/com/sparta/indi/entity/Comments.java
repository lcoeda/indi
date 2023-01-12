package com.sparta.indi.entity;

import jakarta.persistence.*;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comments;

    public Comments(){
        this.username = username;
        this.comments = comments;
    }
}
