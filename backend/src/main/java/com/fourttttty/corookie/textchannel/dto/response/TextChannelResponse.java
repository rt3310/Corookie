package com.fourttttty.corookie.textchannel.dto.response;

import com.fourttttty.corookie.textchannel.domain.TextChannel;
import lombok.Builder;

@Builder
public record TextChannelResponse(String name) {

    public static TextChannelResponse from(TextChannel textChannel) {
        return TextChannelResponse.builder()
                .name(textChannel.getChannelName())
                .build();
    }
}
