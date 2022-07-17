package com.drpicox.game.card.api;

import com.drpicox.game.card.CardPositionService;
import com.drpicox.game.card.CardService;
import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.game.api.GameDTOFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game/cards")
public class CardController {

    private final CardService cardService;
    private final CardPositionService cardPositionService;
    private final GameDTOFactory gameDTOFactory;

    public CardController(CardService cardService, CardPositionService cardPositionService, GameDTOFactory gameDTOFactory) {
        this.cardService = cardService;
        this.cardPositionService = cardPositionService;
        this.gameDTOFactory = gameDTOFactory;
    }

    @PostMapping("/{cardId}/move")
    public GameDTO moveCard(@PathVariable String cardId, @RequestBody MoveCardDTO moveCardDTO) {
        var card = cardService.findById(cardId).get();
        cardPositionService.moveCard(card, moveCardDTO.getPosition(), moveCardDTO.getZindex());
        return gameDTOFactory.makeGameDTO();
    }

    @PostMapping("/{cardId}/discard")
    public GameDTO discardCard(@PathVariable String cardId) {
        var card = cardService.findById(cardId).get();
        cardService.discardCard(card);
        return gameDTOFactory.makeGameDTO();
    }
}
