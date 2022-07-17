package com.drpicox.game.idea;

import com.drpicox.game.util.Settings;

public class IdeaFactorySettings extends Settings {

    public IdeaFactorySettings(String ideaName) {
        set("ideaName", ideaName);
    }

    public IdeaFactorySettings withLevel(int level) {
        set("level", level);
        return this;
    }

    public IdeaFactorySettings withXp(int xp) {
        set("xp", xp);
        return this;
    }

    public String getIdeaName() {
        return get("ideaName");
    }

    public int getLevel() {
        return getOrDefault("level", 1);
    }

    public int getXp() {
        return getOrDefault("xp", 0);
    }
}
