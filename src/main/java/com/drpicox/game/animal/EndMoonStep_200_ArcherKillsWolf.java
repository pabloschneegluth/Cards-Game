package com.drpicox.game.animal;

import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_ArcherKillsWolf implements EndMoonStep{
    private final CardService cardService;

    public EndMoonStep_200_ArcherKillsWolf(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var wolfs = cardService.findAllByName("Wolf");
        var archers = cardService.findAllByName("Archer");
        var arrows = cardService.findAllByName("Arrow");

            wolfs.stream().forEach(wolf -> {
            var arrow = arrows.stream().findAny();
            archers.stream().findAny().ifPresent(card -> {
                cardService.discardCard(arrow.get());
                cardService.discardCard(wolf);
            });
        });
    }
}
