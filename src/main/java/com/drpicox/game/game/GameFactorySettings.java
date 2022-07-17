package com.drpicox.game.game;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.util.Settings;

public class GameFactorySettings extends Settings {
    public GameFactorySettings() {
        this("default");
    }

    public GameFactorySettings(String gameName) {
        set("gameName", gameName);
    }

    public GameFactorySettings withGameConstants(Constants gameConstants) {
        set("gameConstants", gameConstants);
        return this;
    }

    public Constants getGameConstants() {
        return get("gameConstants");
    }

    public String getGameName() {
        return get("gameName");
    }

}
