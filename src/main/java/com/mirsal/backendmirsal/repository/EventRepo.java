package com.mirsal.backendmirsal.repository;

import com.mirsal.backendmirsal.model.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event,Long> {
}
