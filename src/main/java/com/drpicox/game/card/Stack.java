package com.drpicox.game.card;

import com.drpicox.game.util.HasNames;

import java.util.ArrayList;
import java.util.List;

public class Stack implements HasNames {

    private final int position;
    private final List<Card> cards;

    public Stack(int position, List<Card> cards) {
        this.position = position;
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> stackOver(List<Card> cards) {
        var zindex = getMaxZindex();
        for (var card: cards) {
            zindex += 1;
            card.placeAt(position, zindex);
            this.cards.add(card);
        }

        return cards;
    }

    public List<Card> cut(Card card) {
        var cardIndex = cards.indexOf(card);
        var topList = cards.subList(cardIndex, cards.size());

        var result = new ArrayList<>(topList);
        topList.clear();

        return result;
    }

    public ArrayList<Card> insert(Card card, int zindex) {
        var bottomCards = new ArrayList<Card>();
        var topCards = new ArrayList<Card>();

        this.cards.remove(card);
        for (var otherCard: cards) {
            var list = otherCard.getZindex() < zindex ? bottomCards : topCards;
            list.add(otherCard);
        }

        card.placeAt(position, zindex);
        this.cards.add(bottomCards.size(), card);

        for (var topCard: topCards) {
            zindex += 1;
            topCard.placeAt(position, zindex);
        }

        var affectedCards = new ArrayList<Card>();
        affectedCards.add(card);
        affectedCards.addAll(topCards);
        return affectedCards;
    }

    public int getPosition() {
        return position;
    }

    public Card getBottomCard() {
        if (cards.isEmpty()) return null;

        var bottom = cards.get(0);
        return bottom;
    }

    public Card getTopCard() {
        if (cards.isEmpty()) return null;

        var top = cards.get(cards.size() - 1);
        return top;
    }

    public int getMaxZindex() {
        var top = getTopCard();
        return top == null ? -1 : top.getZindex();
    }

    @Override
    public int size() {
        return cards.size();
    }

    @Override
    public String getName(int index) {
        return cards.get(index).getName();
    }

    public CardListSummary getSummary(int fromIndex, int toIndexExclusive) {
        var size = cards.size();
        fromIndex = Math.min(size - 1, fromIndex);
        toIndexExclusive = Math.min(size, toIndexExclusive);
        return new CardListSummary(cards.subList(fromIndex, toIndexExclusive));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Stack) obj;
        return position == other.position;
    }

    @Override
    public String toString() {
        return "#" + position + "{" + cards + "}";
    }

}
