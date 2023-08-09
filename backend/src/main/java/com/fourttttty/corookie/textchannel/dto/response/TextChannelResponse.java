package com.fourttttty.corookie.textchannel.dto.response;

import com.fourttttty.corookie.textchannel.domain.TextChannel;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TextChannelResponse(@NotBlank String name) {

    public static TextChannelResponse from(TextChannel textChannel) {
        return TextChannelResponse.builder()
                .name(textChannel.getChannelName())
                .build();
    }
}
