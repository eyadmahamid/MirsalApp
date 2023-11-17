package com.mirsal.backendmirsal.model.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "event_location")
    private String location;

    @Column(name = "event_occasion")
    private String occasion;

    @Column(name = "event_desc")
    private String description;


    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

//    @Column(name = "organizer_id")
//    private Long organizer_id;

//    the Manager who created the event
@ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "event_managers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<User> managers;



    public void addManager(User manager) {
        if (this.managers == null) {
            this.managers = new ArrayList<>();
        }
        this.managers.add(manager);
    }

}