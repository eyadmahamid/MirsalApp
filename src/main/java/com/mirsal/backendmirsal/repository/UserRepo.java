package com.mirsal.backendmirsal.repository;

import com.mirsal.backendmirsal.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    User findByEmailOrUsername(String email,String username);

}
