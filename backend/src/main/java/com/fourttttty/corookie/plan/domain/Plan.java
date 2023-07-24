package com.fourttttty.corookie.plan.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name="plan")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private String planName;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private LocalDateTime planStart;

    @Column(nullable = false)
    private LocalDateTime planEnd;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean enabled;

//    @ManyToMany
//    @JoinTable(
//    name = "plan_member",
//    joinColumns = @JoinColumn(name = "plan_id"),
//    inverseJoinColumns = @JoinColumn(name = "member_id")
//    )
//    private List<Project> projects;


//    @OneToMany
//    @JoinColumn(name="plan_id")
//    private List<category> categories = new ArrayList<>();

    public Plan(String planName,
                String description,
                LocalDateTime planStart,
                LocalDateTime planEnd){
        this.planName = planName;
        this.description = description;
        this.planStart = planStart;
        this.planEnd = planEnd;
    }

    public void update(String planName,
                       String description,
                       LocalDateTime planStart,
                       LocalDateTime planEnd){
        this.planName = planName;
        this.description = description;
        this.planStart = planStart;
        this.planEnd = planEnd;
    }

    public void delete(){
        this.enabled = false;
    }
}
