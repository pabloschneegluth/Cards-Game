package com.drpicox.game.idea;

import com.drpicox.game.util.PossiblePick;

import java.io.Serializable;

public class IdeaCardReward extends PossiblePick implements Serializable {
    private int level;

    public IdeaCardReward(String cardName, int level, int possibilities) {
        super(cardName, possibilities);
        this.level = level;
    }

    public String getCardName() {
        return getName();
    }

    public int getLevel() {
        return level;
    }
}
