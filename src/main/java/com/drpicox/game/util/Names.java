package com.drpicox.game.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import static com.drpicox.game.util.Positions.byPosition;

public class Names implements Predicate<HasNames>, Iterable<String>, HasNames {

    public static Predicate<? super HasName> byName(String name) {
        return (hasName) -> hasName.getName().equals(name);
    }

    public static Names byNames(String... names) {
        return new Names().and(names);
    }

    public static Names byNames(int count, String name) {
        return new Names().and(count, name);
    }

    private List<String> names = new ArrayList<>();

    private Names() {}

    public Names and(String ... and) {
        for (var name: and) names.add(name);
        return this;
    }

    public Names and(int count, String name) {
        for (var i = 0; i < count; i += 1) names.add(name);
        return this;
    }

    public String[] get() {
        return names.toArray(String[]::new);
    }

    @Override
    public Iterator<String> iterator() {
        return names.iterator();
    }

    @Override
    public int size() {
        return names.size();
    }

    @Override
    public String getName(int index) {
        return names.get(index);
    }

    @Override
    public boolean test(HasNames hasNames) {
        if (size() != hasNames.size()) return false;
        for (var i = 0; i < size(); i += 1) {
            String expectedName = getName(i);
            String actualName = hasNames.getName(i);
            if (!expectedName.equals(actualName)) return false;
        }

        return true;
    }
}
