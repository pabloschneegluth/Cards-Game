package com.drpicox.game.animal;

import com.drpicox.game.card.GivenStackService;
import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.findAllCard;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221117_FriendlyWolfKillWolf_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GivenStackService givenStackService;
    private GameDTO gameDTO;

    Post_20221117_FriendlyWolfKillWolf_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewGameWithAStackOfNSCardsAndNSCards(int n1, String s1, int n2, String s2) {
        // text:  * Given a new game with a stack of 1 "Friendly Wolf" cards and 1 "Wolf" cards.
        // code: this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Friendly Wolf", 1, "Wolf")
        // hint: Post_20221114_Chicken_Context.givenANewGameWithAStackOfNSCardsAndNSCards

        // Add here what is given
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNSCards(int expected, String s1) {
        // text:  * There should be 0 "Friendly Wolf" cards.
        // code: this.thereShouldBeNSCards(0, "Friendly Wolf")
        // hint: Post_20221114_WolfKillVillager_Context.thereShouldBeNSCards

        var cards = findAllCard(gameDTO, byName(s1));
        assertThat(cards).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
