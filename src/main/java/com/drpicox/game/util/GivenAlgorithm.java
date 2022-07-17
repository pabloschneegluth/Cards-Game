package com.drpicox.game.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class GivenAlgorithm {
    private GivenAlgorithm() {} // static methods only

    public static <T> List<T> given(int count, Supplier<List<T>> supplier, Consumer<T> deleter, Supplier<T> creator) {
        var list = new ArrayList<T>(supplier.get());

        while (list.size() > count) {
            var excess = list.remove(0);
            deleter.accept(excess);
        }

        while (list.size() < count) {
            var missing = creator.get();
            list.add(missing);
        }

        return list;
    }
}
