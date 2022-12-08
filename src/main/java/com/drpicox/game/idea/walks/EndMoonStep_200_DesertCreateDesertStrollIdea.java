package com.drpicox.game.idea.walks;

import com.drpicox.game.card.CardService;
import com.drpicox.game.idea.IdeaFactory;
import com.drpicox.game.idea.IdeaFactorySettings;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_DesertCreateDesertStrollIdea implements EndMoonStep {

    private CardService cardService;
    private IdeaFactory ideaFactory;

    public EndMoonStep_200_DesertCreateDesertStrollIdea(CardService cardService, IdeaFactory ideaFactory) {
        this.cardService = cardService;
        this.ideaFactory = ideaFactory;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var desert = cardService.findCard(name -> name.getName().equalsIgnoreCase("Dessert"));

        if (desert.isPresent()) {
            ideaFactory.makeIdea(new IdeaFactorySettings("Dessert Stroll Idea"));
        }
    }
}
