package com.drpicox.game.idea.walks;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.idea.*;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import com.drpicox.game.util.RandomPickerService;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_900_OldVillageStrollIdea implements EndMoonStep{

        public static final String IDEA_NAME = "Old Village Stroll Idea";

        private final CardFactory cardFactory;
        private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;
        private final IdeaProgressService ideaProgressService;
        private final RandomPickerService randomPickerService;
        private final CardService cardService;
        private final IdeaFactory ideaFactory;

        public EndMoonStep_900_OldVillageStrollIdea(CardFactory cardFactory, IdeaEndMoonStepExecutor ideaEndMoonStepExecutor,
                                                    IdeaProgressService ideaProgressService, RandomPickerService randomPickerService, CardService cardService, IdeaFactory ideaFactory) {
            this.cardFactory = cardFactory;
            this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
            this.ideaProgressService = ideaProgressService;
            this.randomPickerService = randomPickerService;
            this.cardService = cardService;
            this.ideaFactory = ideaFactory;
        }

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
