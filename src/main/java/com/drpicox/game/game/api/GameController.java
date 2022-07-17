package com.drpicox.game.game.api;

import com.drpicox.game.game.GameFactory;
import com.drpicox.game.game.GameFactorySettings;
import com.drpicox.game.game.GameService;
import com.drpicox.game.moon.MoonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    private final GameService gameService;
    private final GameFactory gameFactory;
    private final MoonService moonService;
    private final GameDTOFactory gameDTOFactory;

    public GameController(GameService gameService, GameFactory gameFactory, MoonService moonService, GameDTOFactory gameDTOFactory) {
        this.gameService = gameService;
        this.gameFactory = gameFactory;
        this.moonService = moonService;
        this.gameDTOFactory = gameDTOFactory;
    }

    @GetMapping
    public GameDTO getGame() throws IOException, URISyntaxException {
        if (!gameService.existsGame()) {
            gameFactory.makeGame(new GameFactorySettings());
        }

        var result = gameDTOFactory.makeGameDTO();
        return result;
    }

    @PostMapping("/moon")
    public GameDTO endMoon() {
        moonService.endMoon();
        return gameDTOFactory.makeGameDTO();
    }
}
