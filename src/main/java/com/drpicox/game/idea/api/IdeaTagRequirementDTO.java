package com.drpicox.game.idea.api;

import com.drpicox.game.idea.IdeaTagRequirement;
import com.drpicox.game.util.HasName;

public class IdeaTagRequirementDTO implements HasName {
    private int cardCount;
    private int tagValue;
    private String tagName;

    public IdeaTagRequirementDTO(IdeaTagRequirement requirement) {
        this.cardCount = requirement.getCardCount();
        this.tagValue = requirement.getTagValue();
        this.tagName = requirement.getName();
    }

    public int getTagValue() {
        return tagValue;
    }

    public String getName() {
        return tagName;
    }

    public int getCardCount() {
        return cardCount;
    }
}
