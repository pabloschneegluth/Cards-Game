package com.drpicox.game.animal;

import com.drpicox.game.card.Card;
import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndMoonStep_200_WolfKillVillager implements EndMoonStep {

    private final CardService cardService;

    public EndMoonStep_200_WolfKillVillager(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var wolfs= cardService.findAllByName("Wolf");
        var villagers = cardService.findAllByName("Villager");

        if(wolfs.size() != 0){
            for(var wolf: wolfs){
                for(var villager: villagers)
                    cardService.discardCard(villager);
            }
        }
    }
}
