package com.drpicox.game.game;

import com.drpicox.game.constants.ConstantsLoader;
import com.drpicox.game.util.Steps;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class GameFactory {

    private final ConstantsLoader constantsLoader;
    private final GameRepository gameRepository;
    private final Steps<GameFactorySettings> gameFactorySteps;
    private final Steps<GameFactorySettings> gameDeleteSteps;

    public GameFactory(ConstantsLoader constantsLoader, GameRepository gameRepository, List<GameFactoryStep> gameFactorySteps, List<GameDeleteStep> gameDeleteSteps) {
        this.constantsLoader = constantsLoader;
        this.gameRepository = gameRepository;
        this.gameFactorySteps = Steps.from(gameFactorySteps);
        this.gameDeleteSteps = Steps.from(gameDeleteSteps);
    }

    public void makeGame(GameFactorySettings settings) throws IOException, URISyntaxException {
        deleteOldGameIfExists(settings);

        this.gameRepository.save(new Game(GameService.GAME_ID));

        var gameName = settings.getGameName();
        var gameConstants = constantsLoader.load("game/" + gameName + ".properties");
        gameFactorySteps.execute(settings.withGameConstants(gameConstants));
    }

    private void deleteOldGameIfExists(GameFactorySettings settings) throws IOException, URISyntaxException {
        if (this.gameRepository.existsById(GameService.GAME_ID)) {
            this.gameRepository.deleteById(GameService.GAME_ID);
            gameDeleteSteps.execute(settings);
        }
    }
}
