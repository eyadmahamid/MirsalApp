package com.mirsal.backendmirsal.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

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
    private Long id;

    @Column(name = "event_date")
    private LocalDateTime date;

    @Column(name = "event_location")
    private String location;

    @Column(name = "event_occasion")
    private String occasion;

    @Column(name = "event_desc")
    private String description;
}
