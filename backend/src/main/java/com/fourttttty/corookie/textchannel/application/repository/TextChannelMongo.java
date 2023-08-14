package com.fourttttty.corookie.textchannel.application.repository;

import com.fourttttty.corookie.textchannel.domain.TextChannel;

import java.util.List;
import java.util.Optional;

public class TextChannelMongo implements TextChannelRepository {

    @Override
    public List<TextChannel> findAll() {
        return null;
    }

    @Override
    public List<TextChannel> findByProjectId(Long projectId) {
        return null;
    }

    @Override
    public Optional<TextChannel> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public TextChannel save(TextChannel textChannel) {
        return null;
    }
}
