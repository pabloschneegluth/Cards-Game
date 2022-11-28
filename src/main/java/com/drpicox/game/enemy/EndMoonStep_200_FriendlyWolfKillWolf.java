package com.drpicox.game.enemy;

import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_FriendlyWolfKillWolf implements EndMoonStep {
    private final CardService cardService;

    public EndMoonStep_200_FriendlyWolfKillWolf(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var friendlyWolfs= cardService.findAllByName("Friendly Wolf");
        var wolfs = cardService.findAllByName("Wolf");
        if(friendlyWolfs.size() != 0){
            for(var friendlyWolf: friendlyWolfs){
                for(var wolf: wolfs) {
                    cardService.discardCard(wolf);
                    cardService.discardCard(friendlyWolf);
                }
            }
        }
    }
}
