package com.drpicox.game.tag.api;

import com.drpicox.game.tag.Tag;

public class TagDTO {
    private final int value;

    public TagDTO(Tag tag) {
        this.value = tag.getValue();
    }

    public int getValue() {
        return value;
    }
}
