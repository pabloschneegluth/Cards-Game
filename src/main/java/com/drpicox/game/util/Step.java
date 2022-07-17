package com.drpicox.game.util;

public interface Step<S extends Settings> {
    void execute(S settings);
}
