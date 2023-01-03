package com.sparta.indi.service;

import com.sparta.indi.dto.PostRequestDto;
import com.sparta.indi.entity.Post;
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
        public Post createMemo(PostRequestDto requestDto) {
            Post post = new Post(requestDto);
            postRepository.save(post);
            return post;
        }

        @Transactional(readOnly = true)
        public List<Post> getMemos() {
            return postRepository.findAllByOrderByCreatedAtDesc();
        }

        @Transactional
        public Post getMemo(Long id) {
            Post ticket = postRepository.findById(id).orElseThrow
                (      () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
                );
            return ticket;
        }

        @Transactional
        public Post update(Long id, PostRequestDto requestDto) {
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            if(post.getPw().equals(requestDto.getPw())){
            post.update(requestDto);}
            return post;
        }

        @Transactional
        public String deleteMemo(Long id) {
            postRepository.deleteById(id);
            return "삭제 성공";
        }


}
