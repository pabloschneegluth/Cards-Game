package com.drpicox.game.idea;

import com.drpicox.game.card.Card;

public class IdeaProgress {
    public IdeaProgress(Idea idea, Card ideaCard, int previousLevel) {
        this.idea = idea;
        this.ideaCard = ideaCard;
        this.cardProgress = ideaCard.getProgress();
        this.level = idea.getLevel();
        this.previousLevel = previousLevel;
    }

    public IdeaProgress(Idea idea, Card ideaCard) {
        this(idea, ideaCard, idea.getLevel());
    }

    private Idea idea;
    private Card ideaCard;
    private int cardProgress;
    private int level;
    private int previousLevel;


    public boolean hasBecomeLevel(int targetLevel) {
        return level == targetLevel && previousLevel < targetLevel;
    }

    public int getLevel() {
        return level;
    }

    public boolean hasCompletedProgress() {
        return cardProgress == 0;
    }
}
