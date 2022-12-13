package com.drpicox.game.building;

import com.drpicox.game.card.*;
import com.drpicox.game.idea.IdeaFactorySettings;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndMoonStep_200_FishFarmGivesFish implements EndMoonStep {

    private CardFactory cardFactory;
    private final StackService stackService;
    private final CardService cardService;

    public EndMoonStep_200_FishFarmGivesFish(CardFactory cardFactory, StackService stackService, CardService cardService) {
        this.cardFactory = cardFactory;
        this.stackService = stackService;
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var stacks = stackService.findAllStack();

        for (var stack : stacks) {
            createFish(stack.getCards());
        }
    }

    private void createFish(List<Card> cards) {
        for (var fishFarm : cards) {
            if(cards.isEmpty())
                return;
            else if(fishFarm.getName().equalsIgnoreCase("Fish Farm")){
                cardFactory.makeCards(1, new CardFactorySettings("Fish").withPosition(fishFarm.getPosition()));

            }
        }
    }
}
