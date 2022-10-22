package com.drpicox.game.idea;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221017_BuildIdea_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    Post_20221017_BuildIdea_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
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

    public void givenANewGameWithAStackOfNSCardsNSAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * Given a new game with a stack of 1 "Build Idea" cards, 1 "Villager" and 4 "Wood" cards.
        // code: this.givenANewGameWithAStackOfNSCardsNSAndNSCards(1, "Build Idea", 1, "Villager", 4, "Wood")
        // hint: Post_20221017_Houses_Context.givenANewGameWithAStackOfNSCardsNSAndNSCards

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        throw new UnsupportedOperationException("The method givenANewGameWithAStackOfNSCardsNSAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3) is not implemented yet.");
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String s1, int n1, int n2) {
        // text:  * Given there is the "Woods Stroll Idea" idea at level 3 and 9 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 3, 9)
        // hint: Post_20221017_Houses_Context.givenThereIsTheSIdeaAtLevelNAndNXp

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        throw new UnsupportedOperationException("The method givenThereIsTheSIdeaAtLevelNAndNXp(String s1, int n1, int n2) is not implemented yet.");
    }

    public void thereShouldBeNoSIdea(String expected) {
        // text:  * There should be no "Build Idea" idea.
        // code: this.thereShouldBeNoSIdea("Build Idea")
        // hint: Post_20221017_BuildingIdea_Context.thereShouldBeNoSIdea

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);


        throw new UnsupportedOperationException("The method thereShouldBeNoSIdea(String expected) is not implemented yet.");
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221017_Houses_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

        throw new UnsupportedOperationException("The method endTheCurrentMoon() is not implemented yet.");
    }

    public void theSShouldHaveLevelNAndNXp(String s1, int n1, int n2) {
        // text:  * The "Woods Stroll Idea" should have level 4 and 0 XP.
        // code: this.theSShouldHaveLevelNAndNXp("Woods Stroll Idea", 4, 0)
        // hint: Post_20221017_BuildingIdea_Context.theSShouldHaveLevelNAndNXp


        throw new UnsupportedOperationException("The method theSShouldHaveLevelNAndNXp(String s1, int n1, int n2) is not implemented yet.");
    }

    public void thereShouldBeTheSIdea(String expected) {
        // text:  * There should be the "Build Idea" idea.
        // code: this.thereShouldBeTheSIdea("Build Idea")
        // hint: Post_20221017_BuildingIdea_Context.thereShouldBeTheSIdea

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);


        throw new UnsupportedOperationException("The method thereShouldBeTheSIdea(String expected) is not implemented yet.");
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
