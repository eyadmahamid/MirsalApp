package com.mirsal.backendmirsal.repository;

import com.mirsal.backendmirsal.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
