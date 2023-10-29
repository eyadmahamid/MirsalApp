package com.mirsal.backendmirsal.model.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employees")
public class Employee {

    /**
     * @Employees:
     * Purpose: Represents individuals within a company who use the Mirsal app for event management on behalf of the company.
     * Attributes: Employee Name, Contact Information, Employee ID, etc.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emplyee-id")
    private Long id;
    @Column(name = "username")
    private String username;

    @Column(name = "company-name")
    private String companyName;
    @Column(name = "phone")
    private String phoneNumber;


}
