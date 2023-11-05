package com.mirsal.backendmirsal.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    /**
     * @Companies:
     * Purpose: Represents organizations that use the Mirsal app for event management.
     * Attributes: Company Name, Contact Information, Company ID, etc
     */


    @Id
    @Column(name = "company-id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company-name")
    private String companyName;
}
