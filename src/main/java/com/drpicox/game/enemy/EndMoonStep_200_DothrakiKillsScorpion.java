package com.drpicox.game.enemy;

import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_DothrakiKillsScorpion implements EndMoonStep {
    private CardService cardService;

    public EndMoonStep_200_DothrakiKillsScorpion(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var scorpions = cardService.findAllByName("Scorpion");
        var dothrakis = cardService.findAllByName("Dothraki");

        dothrakis.stream().forEach(dothraki -> {
            scorpions.stream().findAny().ifPresent(card -> cardService.discardCard(card));
        });
    }
}
