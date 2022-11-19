package com.drpicox.game.mineral;
import com.drpicox.game.card.*;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndMoonStep_666_makingCoalByBurningWood implements EndMoonStep {

    private final StackService stackService;
    private final CardFactory cardFactory;
    private final CardService cardService;

    public EndMoonStep_666_makingCoalByBurningWood(StackService stackService, CardFactory cardFactory, CardService cardService) {
        super();
        this.stackService = stackService;
        this.cardFactory = cardFactory;
        this.cardService = cardService;
    }

    public void execute(EndMoonSettings settings) {
        var stacks = stackService.findAllStack();

        for (var stack : stacks) {
            burningWood(stack.getCards());
        }
    }

    private void burningWood(List<Card> cards) {
        var flint = cards.stream().filter(card -> card.getName().equalsIgnoreCase("Flint")).findFirst();
        var wood = cards.stream().filter(card -> card.getName().equalsIgnoreCase("Wood")).findFirst();
        var Villager = cards.stream().filter(card -> card.getName().equalsIgnoreCase("Villager")).findFirst();

        if (flint.isEmpty()&& Villager.isEmpty()) return;

        cardFactory.makeCard(new CardFactorySettings("Coal").withPosition(flint.get().getPosition()));
        cardFactory.makeCard(new CardFactorySettings("Coal"));
        cardService.discardCard(wood.get());
    }
}
