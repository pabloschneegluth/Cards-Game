package com.drpicox.game.util;

import java.io.Serializable;

public class PossiblePick implements Serializable, HasName {
    private String name;
    private int possibilities;

    public PossiblePick(String name, int possibilities) {
        this.name = name;
        this.possibilities = possibilities;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getPossibilities() {
        return possibilities;
    }
}
