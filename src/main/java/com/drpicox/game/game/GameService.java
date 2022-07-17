package com.drpicox.game.game;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class GameService {

    static final String GAME_ID = "TheGameId";

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public boolean existsGame() {
        return gameRepository.existsById(GAME_ID);
    }
}
