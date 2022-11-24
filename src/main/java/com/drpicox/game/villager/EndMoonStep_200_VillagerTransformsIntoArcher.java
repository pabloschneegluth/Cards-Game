package com.drpicox.game.villager;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_VillagerTransformsIntoArcher implements EndMoonStep {

    private CardService cardService;
    private CardFactory cardFactory;
    private StackService stackService;

    public EndMoonStep_200_VillagerTransformsIntoArcher(CardService cardService, CardFactory cardFactory, StackService stackService) {
        this.cardService = cardService;
        this.cardFactory = cardFactory;
        this.stackService = stackService;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var stacks = stackService.findAllStack();

        stacks.stream().forEach(stack -> {
            var cards = stack.getCards();

            var villager = cards.stream().filter(name ->
                name.getName().equalsIgnoreCase("villager")).findFirst();

            var magicBook = cards.stream().filter(name ->
                name.getName().equalsIgnoreCase("bow")).findFirst();

            if (villager.isEmpty() == false && magicBook.isEmpty() == false) {
                cardFactory.makeCard(new CardFactorySettings("Archer").withPosition(magicBook.get().getPosition()));
                cardService.discardCard(magicBook.get());
                cardService.discardCard(villager.get());
            }
        });
    }
}
