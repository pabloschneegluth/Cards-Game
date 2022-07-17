package com.drpicox.game.card.api;

public class MoveCardDTO {
    public MoveCardDTO(int position, int zindex) {
        this.position = position;
        this.zindex = zindex;
    }

    public int position;
    public int zindex;

    public int getPosition() {
        return position;
    }

    public int getZindex() {
        return zindex;
    }
}
