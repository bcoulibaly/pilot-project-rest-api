package com.example.demo.repository;

import com.example.demo.model.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<Users, Long> {

    @Transactional
    @Modifying
    void deleteByEmail(String email);

    Optional<Users> findByEmail(String email);
}

