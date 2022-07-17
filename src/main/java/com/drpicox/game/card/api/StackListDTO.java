package com.drpicox.game.card.api;

import com.drpicox.game.game.api.GameDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.drpicox.game.util.OneCollector.toOne;

public final class StackListDTO {
    private StackListDTO() {} // static methods only

    public static List<DerivedStackDTO> findAllStack(GameDTO game) {
        var cards = CardListDTO.findAllCard(game);
        var maxPosition = cards.stream().mapToInt(CardDTO::getPosition).max().orElse(-1);

        var stacks = new ArrayList<DerivedStackDTO>();
        for (int position = 0; position <= maxPosition; position++) {
            stacks.add(getStackByPosition(cards, position));
        }
        return stacks;
    }

    public static List<DerivedStackDTO> findAllStack(GameDTO gameDto, Predicate<? super DerivedStackDTO> predicate) {
        var allStacks = findAllStack(gameDto);
        var result = allStacks.stream().filter(predicate).toList();
        return result;
    }

    public static DerivedStackDTO getStack(GameDTO gameDto, Predicate<? super DerivedStackDTO> predicate) {
        return findAllStack(gameDto).stream().filter(predicate).collect(toOne());
    }

    private static DerivedStackDTO getStackByPosition(List<CardDTO> cards, int position) {
        var stackCards = cards.stream()
            .filter(card -> card.getPosition() == position)
            .toList();

        var stack = new DerivedStackDTO(position, stackCards);
        return stack;
    }

    public static int getFreePosition(GameDTO game) {
        var occupiedPositions = findAllStack(game).stream().map(DerivedStackDTO::getPosition).collect(Collectors.toSet());
        var position = 0;
        while (occupiedPositions.contains(position)) {
            position++;
        }
        return position;
    }
}
