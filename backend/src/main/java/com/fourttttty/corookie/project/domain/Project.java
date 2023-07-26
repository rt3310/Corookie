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
@Getter
@Table(name = "project")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Project extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    public Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private String invitationLink;

    @Column(nullable = false)
    private Boolean invitationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Project(String name,
                   String description,
                   Boolean enabled,
                   String invitationLink,
                   Boolean invitationStatus,
                   Member member) {
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.invitationLink = invitationLink;
        this.invitationStatus = invitationStatus;
        this.member = member;
    }

    public void update(String name,
                       String description,
                       String invitationLink,
                       Boolean invitationStatus) {
        this.name = name;
        this.description = description;
        this.invitationLink = invitationLink;
        this.invitationStatus = invitationStatus;
    }

    public void delete() {
        this.enabled = false;
    }
}
