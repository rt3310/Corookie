package com.fourttttty.corookie.textchannel.application.service;

import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextChannelService {

    private final TextChannelRepository textChannelRepository;

    public List<TextChannel> findAll() {
        return textChannelRepository.findAll();
    }

    public TextChannel findById(Long id) {
        return textChannelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public TextChannel createTextChannel(String name) {
        TextChannel newTextChannel = TextChannel.create(name);
        return textChannelRepository.save(newTextChannel);
    }

}
