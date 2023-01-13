package com.sparta.indi.entity;

import com.sparta.indi.dto.comments.CommentsRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comments extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comments;

    public Comments(CommentsRequestDto commentsRequestDto){
        this.username = commentsRequestDto.getUsername();
        this.comments = commentsRequestDto.getComments();
    }
}
