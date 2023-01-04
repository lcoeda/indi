package com.sparta.indi.controller;

import com.sparta.indi.dto.PostRequestDto;
import com.sparta.indi.dto.PostResponseDto;
import com.sparta.indi.entity.Post;
import com.sparta.indi.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public PostResponseDto createMemo(@RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.createPost(requestDto, request);
    }

    @GetMapping("/posts")
    public List<Post> getMemos(){
        return postService.getMemos();
    }

    @GetMapping("/posts/{id}")
    public Post getMemo(@PathVariable Long id){
        return postService.getMemo(id);
    }

    @PutMapping("/posts/{id}")
    public Post updateMemo(@PathVariable long id, @RequestBody PostRequestDto requestDto){
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/posts/{id}")
    public String deleteMemo(@PathVariable Long id){
        return postService.deleteMemo(id);
    }
}