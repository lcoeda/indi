package com.sparta.indi.repository;

import com.sparta.indi.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    List<Comments> findAllOrderByCreatedAtDesc();
}
