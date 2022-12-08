package com.drpicox.game.villager;

import com.drpicox.game.card.CardService;
import com.drpicox.game.idea.IdeaFactory;
import com.drpicox.game.idea.IdeaFactorySettings;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_WizardCreatesMagicIdea implements EndMoonStep {

    private final CardService cardService;
    private final IdeaFactory ideaFactory;

    public EndMoonStep_200_WizardCreatesMagicIdea(CardService cardService, IdeaFactory ideaFactory) {
        this.cardService = cardService;

        this.ideaFactory = ideaFactory;
    }
    @Override
    public void execute(EndMoonSettings settings) {
        var wizard = cardService.findCard(name ->
            name.getName().equalsIgnoreCase("Wizard"));

        if (wizard.isPresent()) {
            ideaFactory.makeIdea(new IdeaFactorySettings("Magic Idea"));
        }
    }
}
