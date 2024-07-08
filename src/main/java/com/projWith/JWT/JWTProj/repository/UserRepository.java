package com.projWith.JWT.JWTProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projWith.JWT.JWTProj.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}

