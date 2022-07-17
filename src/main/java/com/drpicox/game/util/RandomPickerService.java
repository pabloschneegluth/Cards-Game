package com.drpicox.game.util;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RandomPickerService {

    private final Random random = new Random();

    public <T extends PossiblePick> T pick(String rollName, List<T> items) {
        var totalPossibilities = items.stream().mapToInt(i -> i.getPossibilities()).sum();
        var randomPossibility = random.nextInt(totalPossibilities);

        var currentPossibility = 0;
        var index = 0;
        while (currentPossibility <= randomPossibility) {
            var item = items.get(index);
            currentPossibility += item.getPossibilities();
            index++;
        }
        return items.get(index - 1);
    }
}
