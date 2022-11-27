package com.drpicox.game.enemy;

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
public class Post_20221117_MilitiaKillCreeper_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    Post_20221117_MilitiaKillCreeper_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(2, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
    }

    public void givenThereAreNSCards(int count, String cardName) {
        // text:  * Given there are 1 "Militia" cards.
        // code: this.givenThereAreNSCards(1, "Militia")
        // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNSCards

        // Add here what is given
        givenCardService.givenCards(count, cardName);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221106_Archer_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNSCards(int expected, String cardName) {
        // text:  * There should be 0 "Creeper" cards.
        // code: this.thereShouldBeNSCards(0, "Creeper")
        // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeNSCards

        var cards = findAllCard(gameDTO, byName(cardName));
        assertThat(cards).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
