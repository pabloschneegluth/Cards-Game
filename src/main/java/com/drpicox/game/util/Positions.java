package com.drpicox.game.util;

import java.util.function.Predicate;

public final class Positions {
    private Positions() {} // static methods only (for now)

    public static Predicate<? super HasPosition> byPosition(int position) {
        return (hasPosition) -> hasPosition.getPosition() == position;
    }

}
