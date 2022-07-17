package com.drpicox.game.util;

import java.util.Map;
import java.util.TreeMap;

public class Settings {
    private final Map<String, Object> settings = new TreeMap<>();

    public Settings set(String key, Object value) {
        settings.put(key, value);
        return this;
    }

    public <T> T get(String key) {
        return (T) settings.get(key);
    }

    public <T> T getOrDefault(String key, T absent) {
        return (T) settings.getOrDefault(key, absent);
    }

    public boolean has(String key) {
        return settings.containsKey(key);
    }

    protected void extend(Settings otherSettings) {
        settings.putAll(otherSettings.settings);
    }
}
