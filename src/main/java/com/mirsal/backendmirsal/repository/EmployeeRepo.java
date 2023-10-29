package com.mirsal.backendmirsal.repository;

import com.mirsal.backendmirsal.model.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
}
