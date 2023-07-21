package com.fourttttty.corookie.textchannel.application.repository;

import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.textchannel.infrastructure.TextChannelJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TextChannelRepositoryImpl implements TextChannelRepository {

    private final TextChannelJpaRepository textChannelJpaRepository;

    @Override
    public List<TextChannel> findAll() {
        return textChannelJpaRepository.findAll();
    }

    @Override
    public TextChannel save(TextChannel textChannel) {
        return textChannelJpaRepository.save(textChannel);
    }
}
