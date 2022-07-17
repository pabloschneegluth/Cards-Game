package com.drpicox.game.card.api;

import com.drpicox.game.card.CardService;
import com.drpicox.game.game.api.GameDTOFactorySettings;
import com.drpicox.game.game.api.GameDTOFactoryStep;
import org.springframework.stereotype.Component;

@Component
public class GameDTOFactoryStep_100_Cards implements GameDTOFactoryStep {

    private final CardService cardService;

    public GameDTOFactoryStep_100_Cards(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(GameDTOFactorySettings settings) {
        var cards = new CardListDTO(cardService.findAll());

        var gameDTO = settings.getGameDTO();
        gameDTO.put("cards", cards);
    }
}
