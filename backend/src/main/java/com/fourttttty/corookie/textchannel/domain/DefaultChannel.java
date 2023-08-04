package com.fourttttty.corookie.textchannel.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DefaultChannel {
    NOTICE("공지"),
    FREE("자유"),
    BACKEND("backend"),
    FRONTEND("frontend");

    private final String channelName;
}
