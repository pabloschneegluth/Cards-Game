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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EndMoonStep_900_BuildBone implements EndMoonStep {

    public static final String IDEA_NAME = "Build Idea";
    private final CardFactory cardFactory;
    private final StackService stackService;
    private final CardService cardService;
    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;

    public EndMoonStep_900_BuildBone(CardFactory cardFactory,  StackService stackService, CardService cardService, IdeaEndMoonStepExecutor ideaEndMoonStepExecutor) {
        this.cardFactory = cardFactory;
        this.stackService = stackService;
        this.cardService = cardService;
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
    }

    public void execute(EndMoonSettings settings) {
        ideaEndMoonStepExecutor.execute(settings, IDEA_NAME, this::createBone);
    }

    private void createBone(IdeaEndMoonSettings settings) {
        var cards = settings.getStack().getCards();
        int position = settings.getPosition();

        List<String> stack = Arrays.asList("build idea", "villager", "bone fragment", "bone fragment", "bone fragment");

        Map<String, Integer> materialsNeeded = new HashMap<String, Integer>() {{
            put("bone fragment", 3);
        }};

        if (BuildIdeaUtil.isCorrectStack(cards, stack) == false) {
            return;
        }
        var materials = BuildIdeaUtil.getMaterialsToBuild(materialsNeeded, cards);

        cardFactory.makeCards(1, new CardFactorySettings("Bone").withPosition(position));
        cardService.discardCards(materials);

    }
}
