package com.drpicox.game.card;

import com.drpicox.game.game.GameDeleteStep;
import com.drpicox.game.game.GameFactorySettings;
import org.springframework.stereotype.Component;

@Component
public class GameDeleteStep_100_Cards implements GameDeleteStep {

    private final CardRepository cardRepository;

    public GameDeleteStep_100_Cards(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(GameFactorySettings settings) {
        cardRepository.deleteAll();
    }
}
