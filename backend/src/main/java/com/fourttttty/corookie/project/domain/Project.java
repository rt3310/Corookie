package com.fourttttty.corookie.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue
    @Column(name = "project_id")
    public Long id;

    @Column(name = "project_name")
    private String name;


    @Column(name = "description")
    private String description;


    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "inv_link")
    private boolean invLink;

    @Column(name = "inv_status")
    private boolean invStatus;

    /*
    @OneToOne
    @JoinColumn(name = "owner_id")
    private String ownerId;*/
}
