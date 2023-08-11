package com.fourttttty.corookie.textchannel.application.service;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.textchannel.dto.request.TextChannelCreateRequest;
import com.fourttttty.corookie.textchannel.dto.request.TextChannelModifyRequest;
import com.fourttttty.corookie.textchannel.dto.response.TextChannelResponse;
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
                .map(TextChannelResponse::from)
                .toList();
    }

    public TextChannelResponse findById(Long id) {
        return TextChannelResponse.from(textChannelRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new)
                );
    }

    public List<TextChannelResponse> findByProjectId(Long projectId) {
        return textChannelRepository.findByProjectId(projectId).stream()
                .map(TextChannelResponse::from)
                .toList();
    }

    @Transactional
    public TextChannelResponse create(TextChannelCreateRequest request, Long projectId) {
        return TextChannelResponse.from(textChannelRepository.save(request.toEntity(projectRepository
                        .findById(projectId)
                        .orElseThrow(EntityNotFoundException::new))));
    }

    @Transactional
    public void createDefaultChannel(String channelName, Long projectId) {
        TextChannel.of(channelName, true, false,
                projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new))
                .changeNotDeletableChannel();
    }

    @Transactional
    public TextChannelResponse modify(Long id, TextChannelModifyRequest request) {
        TextChannel textChannel = textChannelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        textChannel.modifyChannelName(request.name());
        return TextChannelResponse.from(textChannel);
    }

    @Transactional
    public void delete(Long id) {
        TextChannel textChannel = textChannelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        textChannel.deleteChannel();
    }

}
