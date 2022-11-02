package com.drpicox.game.idea.builds;

import com.drpicox.game.card.*;
import com.drpicox.game.idea.*;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        createSword(settings);
    }

    private void createFarm(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        int position = settings.getPosition();
        int totalMaterialsNeeded = 0;
        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("stone", 1);
            put("wood", 2);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }

        var materials = getMaterialsToBuild(materialsNeeded, cards);

        if (materials.size() != totalMaterialsNeeded) {
            return;
        }
        cardFactory.makeCards(1, new CardFactorySettings("Farm").withPosition(position));
        cardService.discardCards(materials);
    }

    private void createStoneHouse(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        var position = settings.getPosition();
        int totalMaterialsNeeded = 0;
        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("stone", 4);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }
        var materials = getMaterialsToBuild(materialsNeeded, cards);

        if (materials.size() != totalMaterialsNeeded) {
            return;
        }
        cardFactory.makeCards(1, new CardFactorySettings("Stone House").withPosition(position));
        cardService.discardCards(materials);
    }

    private void createPickAxe(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        var position = settings.getPosition();
        int totalMaterialsNeeded = 0;
        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("iron", 3);
            put("wood", 2);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }
        var materials = getMaterialsToBuild(materialsNeeded, cards);

        if (materials.size() != totalMaterialsNeeded) {
            return;
        }
        cardFactory.makeCards(1, new CardFactorySettings("Pickaxe").withPosition(position));
        cardService.discardCards(materials);

    }

    private List<Card> getMaterialsToBuild(Map<String, Integer> materialsNeeded, List<Card> cards) {
        List<Card> materials = new ArrayList<>();

        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            int numMaterialsNeeded = (set.getValue());

            cards.forEach(card -> {
                var remaining = numMaterialsNeeded - materials.stream().
                    filter(name -> name.getName().equalsIgnoreCase(set.getKey())).toList().size();

                if (card.getName().equalsIgnoreCase(set.getKey()) && remaining != 0) {
                    materials.add(card);
                }
            });
        }

        return materials;
    }

    private void createSword(IdeaEndMoonSettings settings) {
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
        if (countMaterials == 3) {
            cardFactory.makeCards(1,new CardFactorySettings("Sword").withPosition(position));
            cardService.discardCards(wood);
            cardService.discardCards(iron);
        }
    }
}
