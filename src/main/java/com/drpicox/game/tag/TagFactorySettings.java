package com.drpicox.game.tag;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.util.Settings;

public class TagFactorySettings extends Settings {
    public TagFactorySettings(String cardId) {
        super();
        set("cardId", cardId);
    }

    public TagFactorySettings withCardConstants(Constants cardConstants) {
        set("cardConstants", cardConstants);
        return this;
    }

    public String getCardId() {
        return get("cardId");
    }

    public Constants getCardConstants() {
        return get("cardConstants");
    }
}
