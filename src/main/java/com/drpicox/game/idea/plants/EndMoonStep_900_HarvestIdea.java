package com.drpicox.game.idea.plants;

import com.drpicox.game.card.*;
import com.drpicox.game.idea.*;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EndMoonStep_900_HarvestIdea implements EndMoonStep {

    public static final String IDEA_NAME = "Harvest Idea";

    private final CardFactory cardFactory;
    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;
    private final IdeaFactory ideaFactory;
    private final IdeaProgressService ideaProgressService;

    public EndMoonStep_900_HarvestIdea(CardFactory cardFactory, IdeaEndMoonStepExecutor ideaEndMoonStepExecutor, IdeaFactory ideaFactory, IdeaProgressService ideaProgressService) {
        this.cardFactory = cardFactory;
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
        this.ideaFactory = ideaFactory;
        this.ideaProgressService = ideaProgressService;
    }

    public void execute(EndMoonSettings settings) {
        ideaEndMoonStepExecutor.execute(settings, IDEA_NAME, this::executeIdea);
    }

    private void executeIdea(IdeaEndMoonSettings settings) {
        var summary = settings.getSummary();
        var position = settings.getPosition();

        var progress = ideaProgressService.progress(settings, 1);
        if (progress.hasBecomeLevel(2)) ideaFactory.makeIdea(new IdeaFactorySettings("Seed Idea"));

        var plant = summary.getCardByDescriptionTermAndTagName("Fruit", "Fruit Plant");
        var fruitName = plant.getDescriptionTerm("Fruit");

        var level = progress.getLevel();
        cardFactory.makeCards(1 + level, new CardFactorySettings(fruitName).withPosition(position));
    }
}
