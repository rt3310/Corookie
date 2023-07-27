package com.fourttttty.corookie.thread.infrastructure;

import com.fourttttty.corookie.thread.domain.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadJpaRepository extends JpaRepository<Thread, Long> {

    List<Thread> findByTextChannelId(Long textChannelId);
}
