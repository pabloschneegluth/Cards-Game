package com.drpicox.game.idea;

import com.drpicox.game.card.Card;
import com.drpicox.game.card.CardService;
import org.springframework.stereotype.Service;

@Service
public class IdeaProgressService {

    private final IdeaService ideaService;
    private final CardService cardService;

    public IdeaProgressService(IdeaService ideaService, CardService cardService) {
        this.ideaService = ideaService;
        this.cardService = cardService;
    }

    public IdeaProgress progress(IdeaEndMoonSettings settings, int maxProgress) {
        return progress(settings.getIdea(), settings.getIdeaCard(), maxProgress);
    }

    private IdeaProgress progress(Idea idea, Card ideaCard, int maxProgress) {
        var cardProgress = cardService.progressCard(ideaCard, maxProgress);
        if (cardProgress != 0) return new IdeaProgress(idea, ideaCard);

        var previousLevel = idea.getLevel();
        ideaService.increaseXp(idea);
        return new IdeaProgress(idea, ideaCard, previousLevel);
    }

}
