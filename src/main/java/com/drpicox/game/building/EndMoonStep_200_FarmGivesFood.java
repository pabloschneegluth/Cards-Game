package com.drpicox.game.building;

import com.drpicox.game.card.*;
import com.drpicox.game.idea.IdeaEndMoonStepExecutor;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndMoonStep_200_FarmGivesFood implements EndMoonStep {

    private CardFactory cardFactory;
    private final StackService stackService;
    private final CardService cardService;

    public EndMoonStep_200_FarmGivesFood(CardFactory cardFactory, StackService stackService, CardService cardService) {
        this.cardFactory = cardFactory;
        this.stackService = stackService;
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var stacks = stackService.findAllStack();

        for (var stack : stacks) {
            createFood(stack.getCards());
        }
    }

    private void createFood(List<Card> cards) {
        boolean isFarm = false;
        boolean isPlant = false;
        String fruit = null;
        int position = 0;

        for (var card : cards) {
            if (!card.getName().equalsIgnoreCase("Corpse") && card.getLooksLike().equalsIgnoreCase("plant")) {
                isPlant = true;
                fruit = card.getDescription().values().stream().findFirst().get();
                position = card.getPosition();
            }
            if (card.getName().equalsIgnoreCase("farm")) {
                isFarm = true;
            }
        }
        if (isFarm == false) {
            return;
        }
        cardFactory.makeCards(5, new CardFactorySettings(fruit).withPosition(position));
    }
}
