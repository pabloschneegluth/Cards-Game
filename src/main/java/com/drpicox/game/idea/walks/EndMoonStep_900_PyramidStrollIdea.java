package com.drpicox.game.idea.walks;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.idea.IdeaEndMoonSettings;
import com.drpicox.game.idea.IdeaEndMoonStepExecutor;
import com.drpicox.game.idea.IdeaFactory;
import com.drpicox.game.idea.IdeaProgressService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import com.drpicox.game.util.RandomPickerService;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_900_PyramidStrollIdea implements EndMoonStep {

    public static final String IDEA_NAME = "Pyramid Stroll Idea";
    private final CardFactory cardFactory;
    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;
    private final RandomPickerService randomPickerService;
    private final IdeaProgressService ideaProgressService;
    private final CardService cardService;



    public EndMoonStep_900_PyramidStrollIdea(CardFactory cardFactory, IdeaEndMoonStepExecutor ideaEndMoonStepExecutor, IdeaProgressService ideaProgressService, RandomPickerService randomPickerService, CardService cardService, IdeaFactory ideaFactory, IdeaProgressService ideaProgressService1, CardService cardService1) {
        this.cardFactory = cardFactory;
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
        this.randomPickerService = randomPickerService;
        this.ideaProgressService = ideaProgressService1;
        this.cardService = cardService1;
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
        var sawmills =  cardService.findAllByName("Pyramid");

        var randomCardName = randomCardPossibility.getCardName();
        cardFactory.makeCards(2, new CardFactorySettings(randomCardName).withPosition(settings.getPosition()));
    }
}
