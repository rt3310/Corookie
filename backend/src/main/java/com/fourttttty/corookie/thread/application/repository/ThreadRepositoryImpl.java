package com.fourttttty.corookie.thread.application.repository;

import com.fourttttty.corookie.thread.domain.Thread;
import com.fourttttty.corookie.thread.infrastructure.ThreadJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ThreadRepositoryImpl implements ThreadRepository {

    private final ThreadJpaRepository threadJpaRepository;

    @Override
    public Thread save(Thread thread) {
        return threadJpaRepository.save(thread);
    }
}
