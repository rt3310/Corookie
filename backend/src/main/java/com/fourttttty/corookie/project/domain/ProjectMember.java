package com.fourttttty.corookie.project.domain;

import com.fourttttty.corookie.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_member")
@NoArgsConstructor
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    //To-Do : 프로젝트에 Member 추가 / 제거 구현
    public ProjectMember(Member member, Project project){
       this.member = member;
       this.project = project;
    }
}
