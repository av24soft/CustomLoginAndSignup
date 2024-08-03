package com.avsoft.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avsoft.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

