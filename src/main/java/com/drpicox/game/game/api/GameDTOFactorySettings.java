package com.drpicox.game.game.api;

import com.drpicox.game.util.Settings;

public class GameDTOFactorySettings extends Settings {

    public GameDTOFactorySettings(GameDTO gameDto) {
        super();

        set("gameDTO", gameDto);
    }

    public GameDTO getGameDTO() {
        return get("gameDTO");
    }

}
