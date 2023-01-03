package com.sparta.indi.entity;

import com.sparta.indi.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String email;

    public Post(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.pw = requestDto.getPw();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

    public void update(PostRequestDto postRequestDto) {
        this.username = postRequestDto.getUsername();
        this.contents = postRequestDto.getContents();
        this.pw = postRequestDto.getPw();
        this.title = postRequestDto.getTitle();
    }

}