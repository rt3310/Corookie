package com.fourttttty.corookie.videochannel.dto.request;

import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.videochannel.domain.VideoChannel;
import jakarta.validation.constraints.NotBlank;

public record VideoChannelCreateRequest(@NotBlank String name) {

    public VideoChannel toEntity(Project project) {
        return VideoChannel.of(name, true, true, project);
    }
}
