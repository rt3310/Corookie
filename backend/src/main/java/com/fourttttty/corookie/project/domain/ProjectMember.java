package com.fourttttty.corookie.project.domain;

import com.fourttttty.corookie.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectMember {
    @EmbeddedId
    private ProjectMemberId id;

    private ProjectMember(Project project, Member member) {
        this.id = new ProjectMemberId(project, member);
    }

    public static ProjectMember of(Project project, Member member) {
        return new ProjectMember(project, member);
    }
}
