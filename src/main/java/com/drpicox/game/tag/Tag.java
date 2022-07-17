package com.drpicox.game.tag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(TagId.class)
public class Tag {
    @Id private String cardId;
    @Id private String tagName;
    private int tagValue; // value is a reserved keyword

    public Tag(String cardId, String tagName, int value) {
        this.cardId = cardId;
        this.tagName = tagName;
        this.tagValue = value;
    }

    public String getCardId() {
        return cardId;
    }

    public String getName() {
        return tagName;
    }

    public int getValue() {
        return tagValue;
    }

    protected Tag() {}

}
