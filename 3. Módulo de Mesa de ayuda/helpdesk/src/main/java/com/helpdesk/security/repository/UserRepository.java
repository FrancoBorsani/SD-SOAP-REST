package com.helpdesk.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.security.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
