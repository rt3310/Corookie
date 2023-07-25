package com.fourttttty.corookie.textchannel.application.service;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.textchannel.dto.TextChannelResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextChannelService {

    private final TextChannelRepository textChannelRepository;
    private final ProjectRepository projectRepository;

    public List<TextChannelResponse> findAll() {
        return textChannelRepository.findAll().stream()
                .map(textChannel -> new TextChannelResponse(textChannel.getChannelName()))
                .toList();
    }

    public TextChannelResponse findById(Long id) {
        return new TextChannelResponse(textChannelRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new)
                .getChannelName());
    }

    public TextChannel findEntityById(Long id) {
        return textChannelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public TextChannelResponse create(String name, Long projectId) {
        return new TextChannelResponse(textChannelRepository
                .save(TextChannel.create(name, projectRepository
                        .findById(projectId)
                        .orElseThrow(EntityNotFoundException::new))).getChannelName());
    }

    @Transactional
    public TextChannelResponse modifyTextChannel(Long id, String name) {
        TextChannel textChannel = textChannelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        textChannel.modifyChannelName(name);
        return new TextChannelResponse(textChannel.getChannelName());
    }

    @Transactional
    public void deleteTextChannel(Long id) {
        textChannelRepository.deleteById(id);
    }

}
