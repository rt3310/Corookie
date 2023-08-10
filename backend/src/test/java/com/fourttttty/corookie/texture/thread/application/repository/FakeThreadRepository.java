package com.fourttttty.corookie.texture.thread.application.repository;

import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.thread.application.repository.ThreadRepository;
import com.fourttttty.corookie.thread.domain.Thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeThreadRepository implements ThreadRepository {

    private long autoIncrementId = 1L;
    private final Map<Long, Thread> store = new HashMap<>();

    @Override
    public Thread save(Thread thread) {
        store.put(autoIncrementId++, thread);
        return thread;
    }

    @Override
    public Optional<Thread> findById(Long threadId) {
        return Optional.ofNullable(store.get(threadId));
    }

    @Override
    public List<Thread> findByTextChannelId(Long textChannelId) {
        return store.values().stream()
                .filter(thread -> thread.getTextChannel().getId().equals(textChannelId))
                .toList();
    }
}
