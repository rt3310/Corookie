package com.fourttttty.corookie.member.infrastructure;

import com.fourttttty.corookie.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
