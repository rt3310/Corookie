package com.fourttttty.corookie.thread.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fourttttty.corookie.global.exception.InvalidEmojiException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Emoji {
    THUMB(0L, "thumb"),
    HAPPY(1L, "happy"),
    SAD(2L, "sad");

    private final Long id;
    private final String value;

    @JsonCreator
    public static Emoji from(String value) {
        for (Emoji emoji : Emoji.values()) {
            if (emoji.getValue().equals(value)) {
                return emoji;
            }
        }
        throw new InvalidEmojiException();
    }

    @JsonValue
    public Long getId() { return this.id; }

    @JsonValue
    public String getValue() { return this.value; }
}
