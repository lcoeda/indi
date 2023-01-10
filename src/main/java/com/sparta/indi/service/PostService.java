package com.sparta.indi.service;

import com.sparta.indi.dto.PostRequestDto;
import com.sparta.indi.dto.PostResponseDto;
import com.sparta.indi.entity.Post;
import com.sparta.indi.entity.User;
import com.sparta.indi.entity.UserRoleEnum;
import com.sparta.indi.jwt.JwtUtil;
import com.sparta.indi.repository.PostRepository;
import com.sparta.indi.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.saveAndFlush(new Post(requestDto, user.getId()));

            return new PostResponseDto(post);
        } else {
            return null;
        }
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
