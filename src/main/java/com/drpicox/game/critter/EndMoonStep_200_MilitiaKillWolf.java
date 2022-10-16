package com.drpicox.game.critter;

import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_MilitiaKillWolf implements EndMoonStep {
    private final CardService cardService;

    public EndMoonStep_200_MilitiaKillWolf(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings){
        var wolfs= cardService.findAllByName("Wolf");
        for(var wolf: wolfs){
            cardService.discardCard(wolf);
        }
    }

}
