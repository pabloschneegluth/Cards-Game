package com.drpicox.game.card;

import com.drpicox.game.game.GameFactorySettings;
import com.drpicox.game.game.GameFactoryStep;
import org.springframework.stereotype.Component;

@Component
public class GameFactoryStep_100_Cards implements GameFactoryStep {

    private final CardFactory cardFactory;

    public GameFactoryStep_100_Cards(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    @Override
    public void execute(GameFactorySettings settings) {
        var gameConstants = settings.getGameConstants();
        var initialCounts = gameConstants.getCsvTable("card.initialCounts");

        for (var row : initialCounts.getRows()) {
            var cardName = row.get("cardName");
            var initialCount = row.getInt("initialCount");

            cardFactory.makeCards(initialCount, new CardFactorySettings(cardName));
        }
    }
}
