package com.drpicox.game.enemy;

import org.springframework.stereotype.Component;
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
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
        
        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
        throw new UnsupportedOperationException("Please, review the implementation of beforeTest() and remove this exception when it is correct.");
    }

    public void givenThereAreNSCards(int n1, String s1) {
        // text:  * Given there are 1 "Militia" cards.
        // code: this.givenThereAreNSCards(1, "Militia")
        // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNSCards

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        throw new UnsupportedOperationException("The method givenThereAreNSCards(int n1, String s1) is not implemented yet.");
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221106_Archer_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

        throw new UnsupportedOperationException("The method endTheCurrentMoon() is not implemented yet.");
    }

    public void thereShouldBeNSCards(int expected, String s1) {
        // text:  * There should be 0 "Creeper" cards.
        // code: this.thereShouldBeNSCards(0, "Creeper")
        // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeNSCards

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);


        throw new UnsupportedOperationException("The method thereShouldBeNSCards(int expected, String s1) is not implemented yet.");
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
