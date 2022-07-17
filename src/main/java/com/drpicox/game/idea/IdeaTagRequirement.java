package com.drpicox.game.idea;

import com.drpicox.game.util.HasName;

import java.io.Serializable;

public class IdeaTagRequirement implements HasName, Serializable {
    public IdeaTagRequirement(int cardCount, int tagValue, String tagName) {
        this.cardCount = cardCount;
        this.tagValue = tagValue;
        this.tagName = tagName;
    }

    private int cardCount;
    private int tagValue;
    private String tagName;

    public int getCardCount() {
        return cardCount;
    }

    public int getTagValue() {
        return tagValue;
    }

    public String getName() {
        return tagName;
    }
}
