package com.drpicox.game.card;

import java.util.ArrayList;
import java.util.List;

import static com.drpicox.game.util.OneCollector.toOne;

public class CardListSummary {
    public CardListSummary(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> cards;

    public int sumTagValue(String tagValue) {
        return cards.stream().mapToInt(card -> card.getTagValue(tagValue)).sum();
    }

    public List<Card> findAllCardByDescriptionTermAndTagName(String term, String tagName) {
        return cards.stream()
            .filter(card -> card.getDescriptionTerm(term) != null)
            .filter(card -> card.getTagValue(tagName) > 0)
            .toList();
    }

    public Card getCardByDescriptionTermAndTagName(String term, String tagName) {
        return findAllCardByDescriptionTermAndTagName(term, tagName)
            .stream().collect(toOne());
    }
}
