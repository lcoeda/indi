package com.sparta.indi.controller;

import com.sparta.indi.dto.post.PostRequestDto;
import com.sparta.indi.dto.post.PostResponseDto;
import com.sparta.indi.entity.Post;
import com.sparta.indi.security.UserDetailsImplement;
import com.sparta.indi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImplement detailsImplement) {
        PostResponseDto responseDto = postService.createPost(requestDto, detailsImplement.getUser());
        return responseDto;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getMemo(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable long id, @RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImplement userDetailsImplement) {
        return postService.update(id, requestDto, userDetailsImplement.getUser());
    }

    @DeleteMapping("/posts/{id}")
    public String deletePosts(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImplement userDetailsImplement) {
        postService.deletePosts(id, userDetailsImplement.getUser());
        return "삭제 성공";
    }
}