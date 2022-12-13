package com.drpicox.game.enemy;

import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_ArcherKillsArpia implements EndMoonStep {
    private final CardService cardService;

    public EndMoonStep_200_ArcherKillsArpia(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var archers= cardService.findAllByName("Archer");
        var arpias = cardService.findAllByName("Arpia");
        if(archers.size() != 0){
            for(var archer: archers){
                for(var arpia: arpias) {
                    cardService.discardCard(arpia);
                }
            }
        }
    }
}
