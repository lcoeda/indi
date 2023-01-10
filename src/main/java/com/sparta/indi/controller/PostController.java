package com.sparta.indi.controller;

import com.sparta.indi.dto.PostRequestDto;
import com.sparta.indi.entity.Post;
import com.sparta.indi.security.UserDetailsImplement;
import com.sparta.indi.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public String createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        postService.createPost(requestDto, request);
        return "게시글이 등록됐습니다";
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