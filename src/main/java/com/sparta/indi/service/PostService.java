package com.sparta.indi.service;

import com.sparta.indi.dto.PostRequestDto;
import com.sparta.indi.dto.PostResponseDto;
import com.sparta.indi.entity.Post;
import com.sparta.indi.entity.User;
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
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Post post = postRepository.saveAndFlush(new Post(requestDto, user.getId()));

            return new PostResponseDto(post);
        } else {
            return null;
        }
    }


    @Transactional(readOnly = true)
    public List<Post> getMemos() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public Post getMemo(Long id) {
        Post find_id = postRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
                );
        return find_id;
    }


    @Transactional
    public Post update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (post.getUsername().equals(requestDto.getUsername())) {
            post.update(requestDto);
        }
        return post;
    }

    @Transactional
    public String deleteMemo(Long id) {
        postRepository.deleteById(id);
        return "삭제 성공";
    }


}
