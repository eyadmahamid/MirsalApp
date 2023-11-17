package com.mirsal.backendmirsal.model.Entity;


import com.mirsal.backendmirsal.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted_at is null")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username",unique = true)
    @NotNull
        private String username;

    @Column(name = "email",unique = true)
    @Email
    @NotNull
        private String email;

    @Column(name = "password")
    @NotNull
        private String password;

    @Column(name = "phone")

        private String phoneNumber;

    @Column(name = "is_active",columnDefinition = "boolean default false")
    private boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
        private Role user_role;


//    The user can be a 'MANAGER' to create many events
    @OneToMany(mappedBy = "organizer")
    private List<Event> organizedEvents;



}



