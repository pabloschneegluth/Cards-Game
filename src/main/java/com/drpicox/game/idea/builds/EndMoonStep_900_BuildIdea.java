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
    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;

    public EndMoonStep_900_BuildIdea(CardFactory cardFactory,  StackService stackService, CardService cardService, IdeaEndMoonStepExecutor ideaEndMoonStepExecutor) {
        this.cardFactory = cardFactory;
        this.stackService = stackService;
        this.cardService = cardService;
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
    }

    public void execute(EndMoonSettings settings) {
        ideaEndMoonStepExecutor.execute(settings, IDEA_NAME, this::executeIdea);
    }

    private void executeIdea(IdeaEndMoonSettings settings) {
        createStoneHouse(settings);
        createPickAxe(settings);
        createFarm(settings);
    }

    private void createFarm(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        var stone = cards.stream().filter(name -> name.getName().equalsIgnoreCase("stone")).toList();
        var wood = cards.stream().filter(name -> name.getName().equalsIgnoreCase("wood")).toList();
    }

    private void createStoneHouse(IdeaEndMoonSettings settings) {
        int countStone = 0;
        var stone = cardService.findAllByName("Stone");
        var cards = settings.getStack().getCards();
        var position = settings.getPosition();

        for (var card : cards) {
            if (card.getName().equalsIgnoreCase("Stone"))  {
                countStone++;
            }
        }
        if (countStone == 4) {
            cardFactory.makeCards(1, new CardFactorySettings("Stone House").withPosition(position));
            cardService.discardCards(stone);
        }
    }

    private void createPickAxe(IdeaEndMoonSettings settings) {
        int countMaterials = 0;
        var cards = settings.getStack().getCards();
        var iron = cardService.findAllByName("Iron");
        var wood = cardService.findAllByName("Wood");
        var position = settings.getPosition();

        for (var card : cards) {
            if (card.getName().equalsIgnoreCase("Iron") || card.getName().equalsIgnoreCase("Wood"))  {
                countMaterials++;
            }
        }
        if (countMaterials == 5) {
            cardFactory.makeCards(1,new CardFactorySettings("Pickaxe").withPosition(position));
            cardService.discardCards(wood);
            cardService.discardCards(iron);
        }
    }
}
