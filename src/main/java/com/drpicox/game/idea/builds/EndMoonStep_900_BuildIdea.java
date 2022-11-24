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
        createWoodHouse(settings);
        createPaper(settings);
        createFishingRod(settings);
        createBook(settings);
        createBow(settings);
        createArrow(settings);
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

    private void createWoodHouse(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        var position = settings.getPosition();
        int totalMaterialsNeeded = 0;
        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("wood", 4);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }
        var materials = getMaterialsToBuild(materialsNeeded, cards);

        if (materials.size() != totalMaterialsNeeded) {
            return;
        }
        cardFactory.makeCards(1, new CardFactorySettings("Wood House").withPosition(position));
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

        int woodcounter=0, ironcounter=0;
        for(Card card : cards){
            if(card.getName().equalsIgnoreCase("wood")) woodcounter++;
            if(card.getName().equalsIgnoreCase("iron")) ironcounter++;
        }
        if(woodcounter!=1)return;
        if(ironcounter!=2)return;

        if (countMaterials == 3) {
            cardFactory.makeCards(1,new CardFactorySettings("Sword").withPosition(position));
            cardService.discardCards(wood);
            cardService.discardCards(iron);
        }
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

    private void createPaper(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        if (cards.size() != 4) return;

        var position = settings.getPosition();
        int totalMaterialsNeeded = 0;
        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("wood", 2);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }
        var materials = getMaterialsToBuild(materialsNeeded, cards);

        cardFactory.makeCards(1, new CardFactorySettings("Paper").withPosition(position));
        cardService.discardCards(materials);
    }

    private void createFishingRod(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        int position = settings.getPosition();
        int totalMaterialsNeeded = 0;
        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("string", 1);
            put("wood", 2);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded = totalMaterialsNeeded + set.getValue();
        }

        var materials = getMaterialsToBuild(materialsNeeded, cards);

        int woodcounter=0, stringcounter=0;
        for(Card card : materials){
            if(card.getName().equalsIgnoreCase("wood")) woodcounter++;
            if(card.getName().equalsIgnoreCase("string")) stringcounter++;
        }
        if(woodcounter!=2)return;
        if(stringcounter!=1)return;

        cardFactory.makeCards(1, new CardFactorySettings("Fishing Rod").withPosition(position));
        cardService.discardCards(materials);
    }

    private void createBook(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        if (cards.size() != 7) return;

        var position = settings.getPosition();
        int totalMaterialsNeeded = 0;
        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("paper", 5);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }
        var materials = getMaterialsToBuild(materialsNeeded, cards);
        if(materials.size()!=5) return;

        cardFactory.makeCards(1, new CardFactorySettings("Book").withPosition(position));
        cardService.discardCards(materials);
    }

    private void createBow(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        if (cards.size() != 7) return;
        
        var position = settings.getPosition();
        int totalMaterialsNeeded = 0;
        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("wood", 3);
            put("string", 2);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }
        var materials = getMaterialsToBuild(materialsNeeded, cards);
        if(materials.size()!=5) return;

        int woodcounter=0, stringcounter=0;
        for(Card card : materials){
            if(card.getName().equalsIgnoreCase("wood")) woodcounter++;
            if(card.getName().equalsIgnoreCase("string")) stringcounter++;
        }
        if(woodcounter!=3)return;
        if(stringcounter!=2)return;

        cardFactory.makeCards(1, new CardFactorySettings("Bow").withPosition(position));
        cardService.discardCards(materials);

        var fishing_rod =  cardService.findAllByName("Fishing Rod");
        cardService.discardCards(fishing_rod);
    }        
        
    private void createArrow(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        if (cards.size() != 4) return;

        var position = settings.getPosition();
        int totalMaterialsNeeded = 0;
        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("stone", 1);
            put("wood", 1);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }
        var materials = getMaterialsToBuild(materialsNeeded, cards);
        if(materials.size()!=2) return;

        cardFactory.makeCards(1, new CardFactorySettings("Arrow").withPosition(position));
        cardService.discardCards(materials);

        var paper =  cardService.findAllByName("Paper");
        cardService.discardCards(paper);
    }
}
