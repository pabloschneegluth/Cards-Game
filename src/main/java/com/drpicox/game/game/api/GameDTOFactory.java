package com.drpicox.game.game.api;

import com.drpicox.game.util.Steps;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameDTOFactory {

    private final Steps<GameDTOFactorySettings> gameDTOFactorySteps;

    public GameDTOFactory(List<GameDTOFactoryStep> steps) {
        this.gameDTOFactorySteps = Steps.from(steps);
    }

    public GameDTO makeGameDTO() {
        var gameDTO = new GameDTO();
        var settings = new GameDTOFactorySettings(gameDTO);
        gameDTOFactorySteps.execute(settings);

        return gameDTO;
    }
}
