package com.drpicox.game.enemy;

import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_DothrakiKillsMummy implements EndMoonStep {

    private final CardService cardService;

    public EndMoonStep_200_DothrakiKillsMummy(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var mummy = cardService.findAllByName("Mummy");
        var dothrakis = cardService.findAllByName("Dothraki");

        if (dothrakis.size() != 0) {
            for (var dothraki : dothrakis) {
                mummy.stream().findAny().ifPresent(card -> cardService.discardCard(card));
            }
        }
    }
}
