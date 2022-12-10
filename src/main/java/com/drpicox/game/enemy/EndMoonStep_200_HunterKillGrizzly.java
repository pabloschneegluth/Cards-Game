package com.drpicox.game.enemy;

import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_HunterKillGrizzly implements EndMoonStep {

    private final CardService cardService;

    public EndMoonStep_200_HunterKillGrizzly(CardService cardService) {
        this.cardService = cardService;
    }

    public void execute(EndMoonSettings settings) {
        var hunters= cardService.findAllByName("Hunter");
        var grizzles = cardService.findAllByName("Grizzly");
        if(hunters.size() != 0){
            for(var hunter: hunters){
                for(var grizzly: grizzles)
                    cardService.discardCard(grizzly);
            }
        }
    }
}
