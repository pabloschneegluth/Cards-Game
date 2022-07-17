package com.drpicox.game.card;

import org.springframework.stereotype.Service;

import java.util.List;

import static com.drpicox.game.util.GivenAlgorithm.given;
import static com.drpicox.game.util.Names.byName;

@Service
public class GivenCardService {

    private final CardService cardService;
    private final CardFactory cardFactory;

    public GivenCardService(CardService cardService, CardFactory cardFactory) {
        this.cardService = cardService;
        this.cardFactory = cardFactory;
    }

    public List<Card> givenCards(int count, String cardName) {
        return givenCards(count, new CardFactorySettings(cardName));
    }

    public List<Card> givenCards(int count, CardFactorySettings settings) {
        var cardName = settings.getCardName();
        var hasPosition = settings.hasPosition();
        var position = settings.getPosition();

        return given(count,
            () -> cardService.findAllCards(byName(cardName)).stream()
                .filter(c -> !hasPosition || c.getPosition() == position)
                .toList(),
            card -> cardService.discardCard(card),
            () -> cardFactory.makeCard(settings)
        );
    }
}
