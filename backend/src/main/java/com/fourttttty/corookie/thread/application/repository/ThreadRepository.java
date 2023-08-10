package com.fourttttty.corookie.thread.application.repository;

import com.fourttttty.corookie.thread.domain.Thread;

import java.util.List;
import java.util.Optional;

public interface ThreadRepository {

    Thread save(Thread thread);

    Optional<Thread> findById(Long threadId);

    List<Thread> findByTextChannelId(Long textChannelId);

}
