package com.drpicox.game.animal;

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
        var militias = cardService.findAllByName("Militia");
        if(militias.size() != 0){
            for(var militia: militias){
                for(var wolf: wolfs)
                    cardService.discardCard(wolf);
            }
        }
    }
}
