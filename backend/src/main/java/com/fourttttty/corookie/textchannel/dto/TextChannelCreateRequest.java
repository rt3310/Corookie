package com.fourttttty.corookie.textchannel.dto;

import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import jakarta.validation.constraints.NotBlank;

public record TextChannelCreateRequest(@NotBlank String channelName) {

    public TextChannel toEntity(Project project) {
        return TextChannel.of(channelName, true, true,  project);
    }
}
