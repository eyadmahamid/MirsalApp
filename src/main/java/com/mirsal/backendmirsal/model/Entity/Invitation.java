package com.mirsal.backendmirsal.model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invitation")
public class Invitation {

    /**
     * @Invitation:
     * Purpose: Represents the invitations sent to users or employees for events.
     * Attributes: Event Details, Recipient, Invitation Method, Invitation Status, etc.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_id")
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

}
