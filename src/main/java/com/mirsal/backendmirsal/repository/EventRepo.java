package com.mirsal.backendmirsal.repository;

import com.mirsal.backendmirsal.model.Entity.Event;
import com.mirsal.backendmirsal.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepo extends JpaRepository<Event,Long> {

    Optional<Event> findByOrganizerId(Long organizer);

    Optional<List<Event>> findAllByOrganizerId(Long organizer_id);

}
