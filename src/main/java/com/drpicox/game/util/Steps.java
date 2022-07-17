package com.drpicox.game.util;

import java.util.ArrayList;
import java.util.List;

public class Steps<S extends Settings> {
    public static <S extends Settings, E extends Step<S>> Steps from(List<E> stepList) {
        var list = new ArrayList<Step<S>>(stepList.size());
        list.addAll(stepList);
        list.sort((a, b) -> a.getClass().getSimpleName().compareTo(b.getClass().getSimpleName()));

        var steps = new Steps<S>(list);
        return steps;
    }

    private final List<Step<S>> steps;
    private Steps(ArrayList<Step<S>> list) {
        this.steps = list;
    }

    public void execute(S settings) {
        for (var step: steps) {
            step.execute(settings);
        }
    }
}
