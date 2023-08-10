package com.fourttttty.corookie.textchannel.application.repository;

import com.fourttttty.corookie.textchannel.domain.TextChannel;

import java.util.List;
import java.util.Optional;

public interface TextChannelRepository {
    List<TextChannel> findAll();

    List<TextChannel> findByProjectId(Long projectId);

    Optional<TextChannel> findById(Long id);

    TextChannel save(TextChannel textChannel);
}
