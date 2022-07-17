package com.drpicox.game.util;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.drpicox.game.util.Names.byName;

@Service
public class RandomPickerServiceMock extends RandomPickerService {

    private Map<String, List<String>> mockedPicks = new TreeMap<>();

    @Override
    public <T extends PossiblePick> T pick(String pickName, List<T> possibilities) {
        if (!mockedPicks.containsKey(pickName)) return possibilities.get(0);

        var nextPicks = mockedPicks.get(pickName);
        var nextPick = nextPicks.remove(0);
        if (nextPicks.isEmpty()) mockedPicks.remove(pickName);

        var next = possibilities.stream().filter(byName(nextPick)).findAny();

        if (!next.isPresent()) throw new AssertionError(
            "The random for pickName '" + pickName + "' was mocked to be " + nextPick + " but it is not present in the list.\n" +
            " - mocked element   : '" + nextPick + "'\n" +
            " - possible elements: '" + listElements(possibilities) + "'\n");

        return (T) next.get();
    }

    private <T extends PossiblePick> String listElements(List<T> items) {
        return items.stream().map(e -> e.getName()).collect(Collectors.joining("', '"));
    }

    public void mockPick(String pickName, String possibilityName) {
        if (!mockedPicks.containsKey(pickName)) {
            mockedPicks.put(pickName, new LinkedList<>());
        }

        var list = mockedPicks.get(pickName);
        list.add(possibilityName);
    }
}
