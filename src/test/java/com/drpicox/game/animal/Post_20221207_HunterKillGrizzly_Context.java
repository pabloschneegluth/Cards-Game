package com.drpicox.game.animal;

import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.findAllCard;
import static com.drpicox.game.util.Names.byName;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221207_HunterKillGrizzly_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    Post_20221207_HunterKillGrizzly_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNSCards(int n1, String s1) {
        // text:  * Given there are 1 "Hunter" cards.
        // code: this.givenThereAreNSCards(1, "Hunter")
        // hint: Post_20221120_ChickenLaysEgg_Context.givenThereAreNSCards

        // Add here what is given
        givenCardService.givenCards(n1, s1);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221120_ChickenLaysEgg_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNSCards(int expected, String s1) {
        // text:  * There should be 0 "Grizzly" cards.
        // code: this.thereShouldBeNSCards(0, "Grizzly")
        // hint: Post_20221117_FriendlyWolfKillWolf_Context.thereShouldBeNSCards

        var cards = findAllCard(gameDTO, byName(s1));
        assertThat(cards).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
