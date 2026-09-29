package com.example.recipebox.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipebox.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}