package com.sparta.indi.controller;

import com.sparta.indi.dto.MemoRequestDto;
import com.sparta.indi.entity.Memo;
import com.sparta.indi.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto){
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos(){
        return memoService.getMemos();
    }

    @PutMapping("/api/memos/{id}")
    public long updateMemo(@PathVariable long id, @RequestBody MemoRequestDto requestDto){
        return memoService.update(id, requestDto);
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        return memoService.deleteMemo(id);
    }
}