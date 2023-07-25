package com.fourttttty.corookie.project.domain;

import com.fourttttty.corookie.member.domain.Member;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_member")
@NoArgsConstructor
public class ProjectMember {
    @EmbeddedId
    private ProjectMemberId id;

    public ProjectMember(Project project, Member member) {
        this.id = new ProjectMemberId(project, member);
    }
}
