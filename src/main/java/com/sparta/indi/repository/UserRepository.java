package com.sparta.indi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<com.sparta.indi.entity.User,Long> {
    List<com.sparta.indi.entity.User> findAllByOrderById();
}