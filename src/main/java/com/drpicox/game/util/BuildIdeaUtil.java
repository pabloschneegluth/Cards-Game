package com.drpicox.game.util;

import com.drpicox.game.card.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class BuildIdeaUtil {

    private BuildIdeaUtil() {

    }

    public static List<Card> getMaterialsToBuild(Map<String, Integer> materialsNeeded, List<Card> cards) {
        List<Card> materials = new ArrayList<>();

        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            int numMaterialsNeeded = (set.getValue());

            cards.forEach(card -> {
                if (card.getName().equalsIgnoreCase(set.getKey())) {
                    materials.add(card);
                }
            });
        }

        return materials;
    }

    public static boolean isCorrectStack(List<Card> cards, List<String> stack) {
        List<String> cardName = cards.stream().map(card -> card.getName().toLowerCase())
            .collect(Collectors.toList());

        List<String> cardNameSorted = cardName.stream().sorted((c1,c2) -> c1.compareTo(c2)).collect(Collectors.toList());
        List<String> stackSorted = stack.stream().sorted((c1,c2) -> c1.compareTo(c2)).collect(Collectors.toList());

        return cardNameSorted.equals(stackSorted);
    }
}
