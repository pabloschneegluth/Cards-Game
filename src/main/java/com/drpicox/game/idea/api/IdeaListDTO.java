package com.drpicox.game.idea.api;

import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.idea.Idea;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.drpicox.game.util.OneCollector.toOne;

public class IdeaListDTO extends ArrayList<IdeaDTO> {

    public IdeaListDTO(List<Idea> ideas) {
        ideas.forEach(idea -> add(new IdeaDTO(idea)));
    }

    public static List<IdeaDTO> findAllIdea(GameDTO game) {
        var result = game.getField("ideas", IdeaListDTO.class);
        return result;
    }

    public static List<IdeaDTO> findAllIdea(GameDTO game, Predicate<? super IdeaDTO> predicate) {
        var result = findAllIdea(game).stream().filter(predicate).toList();
        return result;
    }

    public static IdeaDTO getIdea(GameDTO game, Predicate<? super IdeaDTO> predicate) {
        var result = findAllIdea(game).stream().filter(predicate).collect(toOne());
        return result;
    }

    private IdeaListDTO() {} // GSON required constructor
}
