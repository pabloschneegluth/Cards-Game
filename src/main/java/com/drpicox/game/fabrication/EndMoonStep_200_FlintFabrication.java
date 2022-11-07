package com.drpicox.game.fabrication;

import com.drpicox.game.card.*;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndMoonStep_200_FlintFabrication implements EndMoonStep {

    private final StackService stackService;
    private final CardFactory cardFactory;
    private final CardService cardService;

    public EndMoonStep_200_FlintFabrication(StackService stackService, CardFactory cardFactory, CardService cardService) {
        super();
        this.stackService = stackService;
        this.cardFactory = cardFactory;
        this.cardService = cardService;
    }

    public void execute(EndMoonSettings settings) {
        var stacks = stackService.findAllStack();

        for (var stack : stacks) {
            flintFabrication(stack.getCards());
        }
    }

    private void flintFabrication(List<Card> cards) {
        var pickaxe = cards.stream().filter(card -> card.getName().equalsIgnoreCase("Pickaxe")).findFirst();
        var villager = cards.stream().filter(card -> card.getName().equalsIgnoreCase("Villager")).findFirst();
        var stone = cards.stream().filter(card -> card.getName().equalsIgnoreCase("Stone")).findFirst();

        if (pickaxe.isEmpty()) return;

        cardFactory.makeCard(new CardFactorySettings("Flint").withPosition(stone.get().getPosition()));
        cardFactory.makeCard(new CardFactorySettings("Flint").withPosition(stone.get().getPosition()));
        cardService.discardCard(stone.get());
    }
}
