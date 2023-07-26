package com.fourttttty.corookie.plan.domain;

import com.fourttttty.corookie.global.audit.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PlanCategory extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id", nullable = false)
    private Long id;

    @Column(name = "cate_content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Builder
    public PlanCategory(String content,
        Plan plan) {
        this.content = content;
        this.plan = plan;
    }
}
