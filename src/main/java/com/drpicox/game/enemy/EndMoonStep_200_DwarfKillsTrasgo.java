package com.drpicox.game.enemy;

import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_DwarfKillsTrasgo implements EndMoonStep {
    private CardService cardService;

    public EndMoonStep_200_DwarfKillsTrasgo(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var trasgos = cardService.findAllByName("Trasgo");
        var dwarfs = cardService.findAllByName("Dwarf");

        dwarfs.stream().forEach(dothraki -> {
            trasgos.stream().findAny().ifPresent(card -> cardService.discardCard(card));
        });
    }
}
