package com.fourttttty.corookie.project.domain;

import com.fourttttty.corookie.global.audit.BaseTime;
import com.fourttttty.corookie.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "project")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Project extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    public Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean enabled;

    @Column
    private String invLink;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean invStatus;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Member owner;


    @Builder
    public Project(String name,
                String description,
                String invLink) {
        this.name = name;
        this.description = description;
        this.invLink = invLink;
    }

    public void update(String name,
                       String description) {
        this.name = name;
        this.description = description;
    }

    public void delete(){
        this.enabled = false;
    }
}
