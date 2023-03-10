package com.sparta.indi.dto.post;

import com.sparta.indi.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
//    private String createdAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
//        this.createdAt= String.valueOf(post.getCreatedAt());
    }
}
