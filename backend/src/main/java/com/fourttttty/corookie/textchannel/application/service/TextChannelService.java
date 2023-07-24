package com.fourttttty.corookie.textchannel.application.service;

import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TextChannelService {

    private final TextChannelRepository textChannelRepository;

    public List<TextChannel> findAll() {
        return textChannelRepository.findAll();
    }

    public TextChannel findById(Long id) {
        return textChannelRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public TextChannel createTextChannel(String name) {
        TextChannel newTextChannel = TextChannel.create(name);
        return textChannelRepository.save(newTextChannel);
    }

}
