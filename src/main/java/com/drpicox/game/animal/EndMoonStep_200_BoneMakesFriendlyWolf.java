package com.drpicox.game.animal;

import java.util.List;

import org.springframework.stereotype.Component;

import com.drpicox.game.card.Card;
import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import static com.drpicox.game.util.Names.byName;

@Component
public class EndMoonStep_200_BoneMakesFriendlyWolf implements EndMoonStep {

    private final StackService stackService;
    private final CardFactory cardFactory;
    private final CardService cardService;

    public EndMoonStep_200_BoneMakesFriendlyWolf(StackService stackService, CardFactory cardFactory, CardService cardService) {
        super();
        this.stackService = stackService;
        this.cardFactory = cardFactory;
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var stacks = stackService.findAllStack();

        for (var stack : stacks) {
            convertWolf(stack.getCards());
        }
    }

    private void convertWolf(List<Card> cards) {
        try {
            var boneCard = cards.stream().filter(card -> card.getName().equalsIgnoreCase("Bone")).findFirst().get();
            var wolfCard = cards.stream().filter(card -> card.getName().equalsIgnoreCase("Wolf")).findFirst().get();

            if (boneCard != null && wolfCard != null) {
                cardFactory.makeCard(new CardFactorySettings("Friendly Wolf").withPosition(wolfCard.getPosition()));
                cardService.discardCard(boneCard);
                cardService.discardCard(wolfCard);
            }
        } catch (Exception e) {
        
        }

    }
}
