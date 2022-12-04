package com.drpicox.game.idea.walks;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.idea.IdeaEndMoonSettings;
import com.drpicox.game.idea.IdeaEndMoonStepExecutor;
import com.drpicox.game.idea.IdeaProgressService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import com.drpicox.game.util.RandomPickerService;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_900_DessertStrollIdea implements EndMoonStep {

    public static final String IDEA_NAME = "Dessert Stroll Idea";
    private final CardFactory cardFactory;
    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;
    private final IdeaProgressService ideaProgressService;
    private final RandomPickerService randomPickerService;

    public EndMoonStep_900_DessertStrollIdea(CardFactory cardFactory, IdeaEndMoonStepExecutor ideaEndMoonStepExecutor, IdeaProgressService ideaProgressService, RandomPickerService randomPickerService) {
        this.cardFactory = cardFactory;
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
        this.ideaProgressService = ideaProgressService;
        this.randomPickerService = randomPickerService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        ideaEndMoonStepExecutor.execute(settings, IDEA_NAME, this::executeIdea);
    }

    private void executeIdea(IdeaEndMoonSettings settings) {
        var progress = ideaProgressService.progress(settings, 1);

        var idea = settings.getIdea();
        var cardRewards = idea.getCardRewards();
        var randomCardPossibility = randomPickerService.pick(IDEA_NAME, cardRewards);

        var randomCardName = randomCardPossibility.getCardName();
        cardFactory.makeCards(1, new CardFactorySettings(randomCardName).withPosition(settings.getPosition()));
    }
}
