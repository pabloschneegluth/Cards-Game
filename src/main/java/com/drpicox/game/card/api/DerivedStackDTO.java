package com.drpicox.game.card.api;

import com.drpicox.game.util.HasNames;
import com.drpicox.game.util.HasPosition;

import java.util.List;

public class DerivedStackDTO implements HasPosition, HasNames {

    private int position;
    private List<CardDTO> cards;

    public DerivedStackDTO(int position, List<CardDTO> cards) {
        this.position = position;
        this.cards = cards;
    }

    @Override
    public int getPosition() {
        return position;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    @Override
    public int size() {
        return cards.size();
    }

    @Override
    public String getName(int index) {
        return cards.get(index).getName();
    }

    @Override
    public String toString() {
        return "#" + position + "{" + cards + "}";
    }
}
