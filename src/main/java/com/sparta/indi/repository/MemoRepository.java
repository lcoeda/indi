package com.sparta.indi.repository;

import com.sparta.indi.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByCreatedAtDesc();
}