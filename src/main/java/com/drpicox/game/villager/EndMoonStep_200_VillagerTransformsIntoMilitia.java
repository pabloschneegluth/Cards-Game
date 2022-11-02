package com.drpicox.game.villager;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_VillagerTransformsIntoMilitia implements EndMoonStep {

    private CardService cardService;
    private CardFactory cardFactory;
    private StackService stackService;

    public EndMoonStep_200_VillagerTransformsIntoMilitia(CardService cardService, CardFactory cardFactory, StackService stackService) {
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

            var sword = cards.stream().filter(name ->
                name.getName().equalsIgnoreCase("sword")).findFirst();

            if (villager.isEmpty() == false && sword.isEmpty() == false) {
                cardFactory.makeCard(new CardFactorySettings("Militia").withPosition(sword.get().getPosition()));
                cardService.discardCard(sword.get());
                cardService.discardCard(villager.get());
            }
        });
    }
}
