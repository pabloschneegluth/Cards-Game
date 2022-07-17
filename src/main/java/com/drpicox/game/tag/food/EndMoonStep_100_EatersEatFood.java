package com.drpicox.game.tag.food;

import com.drpicox.game.card.Card;
import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndMoonStep_100_EatersEatFood implements EndMoonStep {

    private final CardService cardService;
    private final CardFactory cardFactory;

    public EndMoonStep_100_EatersEatFood(CardService cardService, CardFactory cardFactory) {
        this.cardService = cardService;
        this.cardFactory = cardFactory;
    }

    public void execute(EndMoonSettings settings) {
        var eaters = cardService.findAllByTagName("Eats");
        var foods = cardService.findAllByTagName("Food");

        var totalEats = eaters.stream().mapToInt(c -> c.getTagValue("Eats")).sum();
        var totalFood = foods.stream().mapToInt(c -> c.getTagValue("Food")).sum();

        while (totalEats > totalFood) {
            var next = eaters.remove(0);
            totalEats -= next.getTagValue("Eats");

            cardFactory.replaceCard(next, "Corpse");
        }

        var remainingToEat = totalEats;
        while (remainingToEat > 0) {
            var plated = removeBestSuitedFood(foods, remainingToEat);
            remainingToEat -= plated.getTagValue("Food");
            cardService.discardCard(plated);
        }
    }

    private Card removeBestSuitedFood(List<Card> foodCards, int remainingToEat) {
        var bestCard = (Card) null;
        var bestFood = 0;
        for (var card: foodCards) {
            var food = card.getTagValue("Food");
            if (food > bestFood && food <= remainingToEat) {
                bestCard = card;
                bestFood = food;
            }
        }

        if (bestCard == null) bestCard = foodCards.get(0);

        foodCards.remove(bestCard);
        return bestCard;
    }
}
