package com.cueshop.repository;

import com.cueshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findUserByEmail(String email);
}
