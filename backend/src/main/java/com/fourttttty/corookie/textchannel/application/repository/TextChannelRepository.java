package com.fourttttty.corookie.textchannel.application.repository;

import com.fourttttty.corookie.textchannel.domain.TextChannel;

import java.util.List;
import java.util.Optional;

public interface TextChannelRepository {
    List<TextChannel> findAll();

    Optional<TextChannel> findById(Long id);

    TextChannel save(TextChannel textChannel);
}
