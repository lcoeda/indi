package com.sparta.indi.service;

import com.sparta.indi.dto.MemoRequestDto;
import com.sparta.indi.entity.Memo;
import com.sparta.indi.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class MemoService {

        private final MemoRepository memoRepository;

        @Transactional
        public Memo createMemo(MemoRequestDto requestDto) {
            Memo memo = new Memo(requestDto);
            memoRepository.save(memo);
            return memo;
        }

        @Transactional(readOnly = true)
        public List<Memo> getMemos() {
            return memoRepository.findAllByOrderByCreatedAtDesc();
        }

        @Transactional
        public Memo getMemo(Long id) {
            Memo ticket = memoRepository.findById(id).orElseThrow
                (      () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
                );
            return ticket;
        }

        @Transactional
        public Memo update(Long id, MemoRequestDto requestDto) {
            Memo memo = memoRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            if(memo.getPassword().equals(requestDto.getPassword())){
            memo.update(requestDto);}
            return memo;
        }

        @Transactional
        public String deleteMemo(Long id) {
            memoRepository.deleteById(id);
            return "삭제 성공";
        }


}
