package com.nari.techshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.techshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}