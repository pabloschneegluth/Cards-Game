package com.drpicox.game.enemy;

import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_MilitiaKillCreeper implements EndMoonStep {
    private final CardService cardService;

    public EndMoonStep_200_MilitiaKillCreeper(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var militians= cardService.findAllByName("Militia");
        var creepers = cardService.findAllByName("Creeper");
        if(militians.size() != 0){
            for(var militia: militians){
                for(var creeper: creepers)
                    cardService.discardCard(creeper);
            }
        }

    }
}
