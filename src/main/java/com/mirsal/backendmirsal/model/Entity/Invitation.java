package com.mirsal.backendmirsal.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "invitation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invitation {

    /**
     * @Invitation:
     * Purpose: Represents the invitations sent to users or employees for events.
     * Attributes: Event Details, Recipient, Invitation Method, Invitation Status, etc.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_id")
    private Long invitationId;


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

    //    The event you are invited to attend
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    //    The Manager responsible for sending the invitation ,manager can send(phone or email)/delete/update/ invitation for users
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User sender;

    @ManyToMany
    @JoinTable(name = "addedInvitations",
            joinColumns = @JoinColumn(name = "invitation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> addByUsers;

}

//org.hibernate.tool.schema.spi.CommandAcceptanceException:
// Error executing DDL "alter table invitation add constraint FKjlhncqm9351scp9mpk6db7xut
// foreign key (event_id) references events (event_id)" via JDBC [Failed to add the foreign
// key constraint. Missing column 'event_id'
// for constraint 'FKjlhncqm9351scp9mpk6db7xut' in the referenced table 'events']