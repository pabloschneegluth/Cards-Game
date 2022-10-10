package com.drpicox.game.game;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221010_CoinsAndPoints_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    Post_20221010_CoinsAndPoints_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
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

    public void enterTheGame() {
        // text:  * Enter the game.
        // code: this.enterTheGame()
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.enterTheGame


        throw new UnsupportedOperationException("The method enterTheGame() is not implemented yet.");
    }

    public void thereShouldBeNSCards(int expected, String s1) {
        // text:  * There should be 7 "Coins" cards.
        // code: this.thereShouldBeNSCards(7, "Coins")
        // hint: Post_20221010_Points_Context.thereShouldBeNSCards

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);


        throw new UnsupportedOperationException("The method thereShouldBeNSCards(int expected, String s1) is not implemented yet.");
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
