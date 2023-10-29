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
    @Column(name = "invitation-id")
    private Long id;

    @Column(name = "event-type")
    private String eventType;


    @Column(name = "date-event")
    private String DateEvent;

}
