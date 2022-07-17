package com.drpicox.game.idea.plants;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.idea.IdeaEndMoonSettings;
import com.drpicox.game.idea.IdeaEndMoonStepExecutor;
import com.drpicox.game.idea.IdeaProgressService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EndMoonStep_900_SeedIdea implements EndMoonStep {

    public static final String IDEA_NAME = "Seed Idea";

    private final CardFactory cardFactory;
    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;
    private final IdeaProgressService ideaProgressService;

    public EndMoonStep_900_SeedIdea(CardFactory cardFactory, IdeaEndMoonStepExecutor ideaEndMoonStepExecutor, IdeaProgressService ideaProgressService) {
        this.cardFactory = cardFactory;
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
        this.ideaProgressService = ideaProgressService;
    }

    public void execute(EndMoonSettings settings) {
        ideaEndMoonStepExecutor.execute(settings, IDEA_NAME, this::executeIdea);
    }

    private void executeIdea(IdeaEndMoonSettings settings) {
        var progress = ideaProgressService.progress(settings, 5);
        if (!progress.hasCompletedProgress()) return;

        var summary = settings.getSummary();
        var seed = summary.getCardByDescriptionTermAndTagName("Plant", "Seed");
        var plantName = seed.getDescriptionTerm("Plant");

        cardFactory.makeCards(1, new CardFactorySettings(plantName).withPosition(settings.getPosition()));
    }
}
