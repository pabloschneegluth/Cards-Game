package com.drpicox.game.idea.walks;

import com.drpicox.game.card.CardService;
import com.drpicox.game.idea.IdeaFactory;
import com.drpicox.game.idea.IdeaFactorySettings;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_PyramidCreatePyramidStrollIdea implements EndMoonStep {

    private CardService cardService;
    private IdeaFactory ideaFactory;

    public EndMoonStep_200_PyramidCreatePyramidStrollIdea(CardService cardService, IdeaFactory ideaFactory) {
        this.cardService = cardService;
        this.ideaFactory = ideaFactory;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var pyramid = cardService.findCard(name -> name.getName().equalsIgnoreCase("Pyramid"));

        if (pyramid.isPresent()) {
            ideaFactory.makeIdea(new IdeaFactorySettings("Pyramid Stroll Idea"));
        }
    }
}
