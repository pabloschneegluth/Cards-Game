package com.drpicox.game.idea;

import com.drpicox.game.game.GameFactorySettings;
import com.drpicox.game.game.GameFactoryStep;
import org.springframework.stereotype.Component;

@Component
public class GameFactoryStep_100_Ideas implements GameFactoryStep {

    private final IdeaFactory ideaFactory;

    public GameFactoryStep_100_Ideas(IdeaFactory ideaFactory) {
        this.ideaFactory = ideaFactory;
    }

    @Override
    public void execute(GameFactorySettings settings) {
        var gameConstants = settings.getGameConstants();
        var initialIdeas = gameConstants.getCsvTable("idea.initial");

        for (var ideaName : initialIdeas.getColumn("ideaName")) {
            ideaFactory.makeIdea(new IdeaFactorySettings(ideaName));
        }
    }
}
