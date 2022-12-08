package com.drpicox.game.util;

import com.drpicox.game.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuildIdeaUtil {

    public static List<Card> getMaterialsToBuild(Map<String, Integer> materialsNeeded, List<Card> cards) {
        List<Card> materials = new ArrayList<>();

        for (Map.Entry<String, Integer> set : materialsNeeded.entrySet()) {
            int numMaterialsNeeded = (set.getValue());

            cards.forEach(card -> {
                var remaining = numMaterialsNeeded - materials.stream().
                    filter(name -> name.getName().equalsIgnoreCase(set.getKey())).toList().size();

                if (card.getName().equalsIgnoreCase(set.getKey()) && remaining != 0) {
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
