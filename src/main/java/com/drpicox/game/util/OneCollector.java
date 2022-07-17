package com.drpicox.game.util;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class OneCollector {
    private OneCollector() {} // static methods only

    public static <T> Collector<T, ?, T> toOne() {
        return Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
                if (list.size() != 1) {
                    throw new IllegalStateException("Stream expected to have one element, but it had " + list.size());
                }
                return list.get(0);
            }
        );
    }
}
