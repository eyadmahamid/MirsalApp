package com.mirsal.backendmirsal.model.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    /**
     * @Entity_User
     * Purpose: Represents individuals who use the Mirsal app to create and manage events and send invitations.
     * Attributes: Name, Contact Information, User ID, etc.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @NotNull
        private String username;

    @Column(name = "email")
    @Email
    @NotNull
        private String email;

    @Column(name = "password")
    @NotNull
        private String password;

    @Column(name = "phone")
        private String phoneNumber;



}
