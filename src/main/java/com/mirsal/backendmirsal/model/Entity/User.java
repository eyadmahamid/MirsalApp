package com.mirsal.backendmirsal.model.Entity;


import com.mirsal.backendmirsal.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @NotNull
        private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
        private Role user_role;


//    The user can be a 'MANAGER' to create many events
    @OneToMany(mappedBy = "organizer")
    private List<Event> organizedEvents;

//  Users Registered in the Mirsal.app can be invited to many events
    @ManyToMany(mappedBy = "invitedUsers")
    private List<Event> eventsAttending;

//    The administrator identifies USERS to ivnite other users
//    Sending the invitation includes application users and others, this is done by sending an (e-mail or phone number).
    @ManyToMany(mappedBy = "addByUsers")
    private List<Invitation> addedInvitaions;

}



