package com.fourttttty.corookie.textchannel.application.repository;

import com.fourttttty.corookie.textchannel.domain.TextChannel;

import java.util.List;

public interface TextChannelRepository {
    List<TextChannel> findAll();

    TextChannel save(TextChannel textChannel);
}
