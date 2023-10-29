package com.mirsal.backendmirsal.repository;

import com.mirsal.backendmirsal.model.Entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepo extends JpaRepository<Invitation,Long> {
}
