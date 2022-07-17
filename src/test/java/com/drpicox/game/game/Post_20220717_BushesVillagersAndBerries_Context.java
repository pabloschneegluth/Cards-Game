package com.drpicox.game.game;

import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.findAllCard;
import static com.drpicox.game.util.Names.byName;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20220717_BushesVillagersAndBerries_Context {

    private final FrontendSimulator frontendSimulator;

    private GameDTO gameDTO;

    public Post_20220717_BushesVillagersAndBerries_Context(FrontendSimulator frontendSimulator) {
        this.frontendSimulator = frontendSimulator;
    }

    public void beforeTest() {
    }

    public void enterTheGame() {
        // example:  * Enter in the game.
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void thereShouldBeNSCards(int expectedCount, String cardName) {
        // example:  * There should be 1 "villager" cards.
        // expected = 1
        // arg1 = "villager"

        var matchingCards = findAllCard(gameDTO, byName(cardName));
        assertThat(matchingCards).hasSize(expectedCount);
    }

    public void afterTest() {
    }
}
