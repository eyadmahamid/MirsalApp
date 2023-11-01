package com.mirsal.backendmirsal.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Event {
    /**
     * @Event:
     * Purpose: Represents a specific gathering or happening organized through the Mirsal app.
     * Attributes: Event Name, Date, Time, Location, Description, Event ID, etc.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_date")
    private Date date;

    @Column(name = "event_location")
    private String location;

    @Column(name = "event_occasion")
    private String occasion;

    @Column(name = "event_desc")
    private String description;

//    the Manager who created the event
    @ManyToOne
    private User organizer;

//    Registered Users who were invited
    @ManyToMany
    @JoinTable(name = "event_invitations",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> invitedUsers;


}
