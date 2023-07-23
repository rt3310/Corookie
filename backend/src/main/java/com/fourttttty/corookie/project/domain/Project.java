package com.fourttttty.corookie.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "project")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    public Long id;

    @Column(name = "project_name")
    private String name;


    @Column(name = "description")
    private String description;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "inv_link")
    private String invLink;

    @Column(name = "inv_status")
    private boolean invStatus;

    /*
    @OneToOne
    @JoinColumn(name = "owner_id")
    private String ownerId;*/
}
