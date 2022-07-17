package com.drpicox.game.card.api;

import com.drpicox.game.card.Card;
import com.drpicox.game.game.api.GameDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.drpicox.game.util.OneCollector.toOne;

public class CardListDTO extends ArrayList<CardDTO> {

    public CardListDTO(List<Card> cards) {
        cards.forEach(card -> add(new CardDTO(card)));
        sort((c1, c2) -> c1.getZindex() - c2.getZindex());
    }

    public static List<CardDTO> findAllCard(GameDTO gameDTO) {
        var result = gameDTO.getField("cards", CardListDTO.class);
        return result;
    }

    public static List<CardDTO> findAllCard(GameDTO gameDTO, Predicate<? super CardDTO> predicate) {
        var result = findAllCard(gameDTO).stream().filter(predicate).toList();
        return result;
    }

    public static CardDTO getCard(GameDTO gameDTO, Predicate<? super CardDTO> predicate) {
        return findAllCard(gameDTO, predicate).stream().collect(toOne());
    }

    private CardListDTO() {} // GSON required constructor

}
