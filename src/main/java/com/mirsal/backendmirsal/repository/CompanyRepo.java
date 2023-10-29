package com.mirsal.backendmirsal.repository;

import com.mirsal.backendmirsal.model.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company,Long> {
}
