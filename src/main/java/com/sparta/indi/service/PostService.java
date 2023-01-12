package com.sparta.indi.service;

import com.sparta.indi.dto.post.PostRequestDto;
import com.sparta.indi.dto.post.PostResponseDto;
import com.sparta.indi.entity.Post;
import com.sparta.indi.entity.User;
import com.sparta.indi.entity.UserRoleEnum;
import com.sparta.indi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {

            Post post = postRepository.saveAndFlush(new Post(requestDto, user.getId()));
            return new PostResponseDto(post);
    }

    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public Post getPost(Long id) {
        Post find_id = postRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
                );
        return find_id;
    }

    @Transactional
    public Post update(long id, PostRequestDto requestDto, User user) {

        Post post = postRepository.findById(id).orElseThrow();
        if (user.getUsername().equals(post.getUsername()) | user.getRole().equals(UserRoleEnum.ADMIN)) {
            post.update(requestDto);
        }
        return post;
    }

    @Transactional
    public void deletePosts(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow();
        if (post.getUsername().equals(user.getUsername())) {
            postRepository.deleteById(id);
        }
    }

}
