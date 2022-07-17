package com.drpicox.game.idea.api;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.game.api.GameDTOFactory;
import com.drpicox.game.idea.IdeaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game/ideas")
public class IdeaController {

    private final IdeaService ideaService;
    private final CardFactory cardFactory;
    private final GameDTOFactory gameDTOFactory;

    public IdeaController(IdeaService ideaService, CardFactory cardFactory, GameDTOFactory gameDTOFactory) {
        this.ideaService = ideaService;
        this.cardFactory = cardFactory;
        this.gameDTOFactory = gameDTOFactory;
    }

    @PostMapping("/{ideaId}/draw")
    public GameDTO drawIdea(@PathVariable String ideaId) {
        var idea = ideaService.findIdeaById(ideaId).get();
        cardFactory.makeCard(new CardFactorySettings(idea.getName()));
        return gameDTOFactory.makeGameDTO();
    }
}
