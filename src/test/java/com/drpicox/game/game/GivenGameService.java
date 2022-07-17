package com.drpicox.game.game;

import com.drpicox.game.game.GameFactory;
import com.drpicox.game.game.GameFactorySettings;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class GivenGameService {

    private GameFactory gameFactory;

    public GivenGameService(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public void givenGame(String gameName) throws IOException, URISyntaxException {
        gameFactory.makeGame(new GameFactorySettings(gameName));
    }

}
