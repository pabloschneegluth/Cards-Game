package com.drpicox.game.animal;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_WolfCallsWolf implements EndMoonStep {
    private final CardService cardService;
    private final CardFactory cardFactory;


    public EndMoonStep_200_WolfCallsWolf(CardService cardService, CardFactory cardFactory) {
        this.cardService = cardService;
        this.cardFactory=cardFactory;
    }
    @Override
    public void execute(EndMoonSettings settings) {
        var wolfs = cardService.findAllByName("Wolf");
        if(wolfs.size() != 0 ){
            cardFactory.makeCards(1, new CardFactorySettings("Wolf"));
        }
    }
}
