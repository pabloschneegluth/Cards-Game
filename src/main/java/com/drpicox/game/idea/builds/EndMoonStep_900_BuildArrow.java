package com.drpicox.game.idea.builds;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.idea.IdeaEndMoonSettings;
import com.drpicox.game.idea.IdeaEndMoonStepExecutor;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import com.drpicox.game.util.BuildIdeaUtil;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EndMoonStep_900_BuildArrow implements EndMoonStep {

    public static final String IDEA_NAME = "Build Idea";
    private final CardFactory cardFactory;
    private final StackService stackService;
    private final CardService cardService;
    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;

    public EndMoonStep_900_BuildArrow(CardFactory cardFactory,  StackService stackService, CardService cardService, IdeaEndMoonStepExecutor ideaEndMoonStepExecutor) {
        this.cardFactory = cardFactory;
        this.stackService = stackService;
        this.cardService = cardService;
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
    }

    public void execute(EndMoonSettings settings) {
        ideaEndMoonStepExecutor.execute(settings, IDEA_NAME, this::createArrow);
    }

    private void createArrow(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        int position = settings.getPosition();
        int totalMaterialsNeeded = 0;

        List<String> stack = Arrays.asList("build idea", "villager", "stone", "wood");

        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("stone", 1);
            put("wood", 1);
        }};

        if (BuildIdeaUtil.isCorrectStack(cards, stack) == false) {
            return;
        }
        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            totalMaterialsNeeded=totalMaterialsNeeded+set.getValue();
        }

        var materials = BuildIdeaUtil.getMaterialsToBuild(materialsNeeded, cards);

        if (materials.size() != totalMaterialsNeeded) {
            return;
        }
        cardFactory.makeCards(1, new CardFactorySettings("Arrow").withPosition(position));
        cardService.discardCards(materials);
    }
}
