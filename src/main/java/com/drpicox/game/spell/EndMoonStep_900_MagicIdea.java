package com.drpicox.game.spell;

import com.drpicox.game.card.Card;
import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.idea.IdeaEndMoonSettings;
import com.drpicox.game.idea.IdeaEndMoonStepExecutor;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EndMoonStep_900_MagicIdea implements EndMoonStep {

    private final String IDEA_NAME = "Magic Idea";

    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;
    private final CardFactory cardFactory;
    private final CardService cardService;

    public EndMoonStep_900_MagicIdea(IdeaEndMoonStepExecutor ideaEndMoonStepExecutor, CardFactory cardFactory, CardService cardService) {
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
        this.cardFactory = cardFactory;
        this.cardService = cardService;
    }


    @Override
    public void execute(EndMoonSettings settings) {
        ideaEndMoonStepExecutor.execute(settings, IDEA_NAME, this::executeIdea);
    }

    private void executeIdea(IdeaEndMoonSettings settings) {
        createGolemSpell(settings);
    }

    private void createGolemSpell(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        var position = settings.getPosition();
        int totalMaterialsNeeded = 0;

        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("bone", 5);
            put("iron", 3);
            put("gold", 1);
        }};
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }
        var materials = getMaterialsToBuild(materialsNeeded, cards);

        cardFactory.makeCards(1, new CardFactorySettings("Golem Spell").withPosition(position));
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
}
