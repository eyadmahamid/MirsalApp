package com.mirsal.backendmirsal.model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;

    @Column(name = "company-name")
    private String companyName;
}
