package com.fourttttty.corookie.textchannel.application.service;

import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
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

//    public TextChannel createRoom(String name) {
//        TextChannel newTextChannel = TextChannel.create(name);
//        return textChannelRepository.save(newTextChannel);
//    }

}
