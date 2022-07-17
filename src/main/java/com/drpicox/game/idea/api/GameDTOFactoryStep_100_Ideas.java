package com.drpicox.game.idea.api;

import com.drpicox.game.game.api.GameDTOFactorySettings;
import com.drpicox.game.game.api.GameDTOFactoryStep;
import com.drpicox.game.idea.IdeaService;
import org.springframework.stereotype.Component;

@Component
public class GameDTOFactoryStep_100_Ideas implements GameDTOFactoryStep {

    private final IdeaService ideaService;

    public GameDTOFactoryStep_100_Ideas(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @Override
    public void execute(GameDTOFactorySettings settings) {
        var ideas = new IdeaListDTO(ideaService.findAll());

        var gameDTO = settings.getGameDTO();
        gameDTO.put("ideas", ideas);
    }
}
