package com.mirsal.backendmirsal.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;


}
