package com.sparta.indi.controller;

import com.sparta.indi.dto.PostRequestDto;
import com.sparta.indi.entity.Post;
import com.sparta.indi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Post createMemo(@RequestBody PostRequestDto requestDto){
        return postService.createMemo(requestDto);
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