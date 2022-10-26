package com.drpicox.game.idea.builds;

import com.drpicox.game.card.*;
import com.drpicox.game.idea.*;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EndMoonStep_900_BuildIdea implements EndMoonStep {

    public static final String IDEA_NAME = "Build Idea";

    private final CardFactory cardFactory;
    private final StackService stackService;
    private final CardService cardService;

    public EndMoonStep_900_BuildIdea(CardFactory cardFactory,  StackService stackService, CardService cardService) {
        this.cardFactory = cardFactory;
        this.stackService = stackService;
        this.cardService = cardService;
    }

    public void execute(EndMoonSettings settings) {
        var stacks = stackService.findAllStack();

        for (var stack : stacks) {
            var cards = stack.getCards();
            createStoneHouse(cards);
        }
    }

    private void createStoneHouse(List<Card> cards) {
        int countStone = 0;
        var stone = cardService.findAllByName("Stone");
        for (var card : cards) {
            if (card.getName().equalsIgnoreCase("Stone"))  {
                countStone++;
            }
        }
        if (countStone == 4) {
            cardFactory.makeCard(new CardFactorySettings("Stone House"));
            cardService.discardCards(stone);
        }
    }
}
