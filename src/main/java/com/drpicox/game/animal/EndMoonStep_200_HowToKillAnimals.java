package com.drpicox.game.animal;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_HowToKillAnimals implements EndMoonStep {
    private final CardService cardService;
    private final CardFactory cardFactory;
    private final StackService stackService;

    public EndMoonStep_200_HowToKillAnimals(CardService cardService, CardFactory cardFactory, StackService stackService) {
        this.cardService = cardService;
        this.cardFactory = cardFactory;
        this.stackService = stackService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var animals= cardService.findAllByName("Cow");
        var militias = cardService.findAllByName("Militia");
        var milk =  cardService.findAllByName("Milk");

            if (militias.size() != 0) {
                for (var militia : militias) {
                    for (var animal : animals) {
                        cardService.discardCards(milk);
                        cardService.discardCard(animal);
                        cardFactory.makeCards(3, new CardFactorySettings("Meat"));
                    }
                }
            }
    }
}
