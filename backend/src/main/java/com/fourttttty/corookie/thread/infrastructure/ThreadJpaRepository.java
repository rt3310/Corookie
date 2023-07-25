package com.fourttttty.corookie.thread.infrastructure;

import com.fourttttty.corookie.thread.domain.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadJpaRepository extends JpaRepository<Thread, Long> {
}
