package com.fourttttty.corookie.plan.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private LocalDateTime planStart;

    @Column
    private LocalDateTime planEnd;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private boolean enabled;

//    @ManyToMany
//    @JoinTable(
//    name = "plan_member",
//    joinColumns = @JoinColumn(name = "plan_id"),
//    inverseJoinColumns = @JoinColumn(name = "member_id")
//    )
//    private List<Project> projects;

//    @ManyToMany
//    @JoinTable(
//    name = "plan_category",
//    joinColumns = @JoinColumn(name = "plan_id"),
//    inverseJoinColumns = @JoinColumn(name = "cate_id")
//    )
//    private List<Category> categories;

}
