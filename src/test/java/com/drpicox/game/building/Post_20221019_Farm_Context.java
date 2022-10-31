package com.drpicox.game.building;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221019_Farm_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    Post_20221019_Farm_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
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

    public void thereShouldBeNSCard(int expected, String s1) {
        // text:  * there should be 0 "Farm" card.
        // code: this.thereShouldBeNSCard(0, "Farm")
        // hint: Post_20221020_WolfCallsWolf_Context.thereShouldBeNSCards

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);


        throw new UnsupportedOperationException("The method thereShouldBeNSCard(int expected, String s1) is not implemented yet.");
    }

    public void givenNSNSNSCards(int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * given 1 "Build Idea", 1 "Stone", 2 "Wood" cards
        // code: this.givenNSNSNSCards(1, "Build Idea", 1, "Stone", 2, "Wood")

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        throw new UnsupportedOperationException("The method givenNSNSNSCards(int n1, String s1, int n2, String s2, int n3, String s3) is not implemented yet.");
    }

    public void givenThereIsTheSCardAtLevelNAndNXp(String s1, int n1, int n2) {
        // text:  * Given there is the "Build Idea" card at level 2 and 6 xp
        // code: this.givenThereIsTheSCardAtLevelNAndNXp("Build Idea", 2, 6)

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        throw new UnsupportedOperationException("The method givenThereIsTheSCardAtLevelNAndNXp(String s1, int n1, int n2) is not implemented yet.");
    }

    public void endTheCurrentMoon() {
        // text:  * end the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221024_Pickaxe_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

        throw new UnsupportedOperationException("The method endTheCurrentMoon() is not implemented yet.");
    }

    public void thereShouldBeNSNSAndNSCards(int expected, String s1, int n2, String s2, int n3, String s3) {
        // text:  * there should be 0 "Stone", 0 "Wood" and 0 "Berry Bush" cards.
        // code: this.thereShouldBeNSNSAndNSCards(0, "Stone", 0, "Wood", 0, "Berry Bush")

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);


        throw new UnsupportedOperationException("The method thereShouldBeNSNSAndNSCards(int expected, String s1, int n2, String s2, int n3, String s3) is not implemented yet.");
    }

    public void givenANewEmptyGame() {
        // text:  * Given a new empty game
        // code: this.givenANewEmptyGame()

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        throw new UnsupportedOperationException("The method givenANewEmptyGame() is not implemented yet.");
    }

    public void givenThereAreNSCards(int n1, String s1) {
        // text:  * Given there are 1 "Farm" cards.
        // code: this.givenThereAreNSCards(1, "Farm")
        // hint: Post_20221020_WolfCallsWolf_Context.givenThereAreNSCards

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        throw new UnsupportedOperationException("The method givenThereAreNSCards(int n1, String s1) is not implemented yet.");
    }

    public void givenThereAreNSCardsOrAnotherPlant(int n1, String s1) {
        // text:  * Given there are 1 "Berry Bush" cards or another plant.
        // code: this.givenThereAreNSCardsOrAnotherPlant(1, "Berry Bush")

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        throw new UnsupportedOperationException("The method givenThereAreNSCardsOrAnotherPlant(int n1, String s1) is not implemented yet.");
    }

    public void thereShouldBeNSCards(int expected, String s1) {
        // text:  * There should be 5 "Berry" cards.
        // code: this.thereShouldBeNSCards(5, "Berry")
        // hint: Post_20221020_WolfCallsWolf_Context.thereShouldBeNSCards

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);


        throw new UnsupportedOperationException("The method thereShouldBeNSCards(int expected, String s1) is not implemented yet.");
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
