package com.drpicox.game.material;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.idea.GivenIdeaService;
import org.springframework.stereotype.Component;

import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221104_MagicComponent_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private GameDTO gameDTO;

    Post_20221104_MagicComponent_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewGameWithAStackOfNSCardsAndNSCards(int n1, String s1, int n2, String s2) {
        // text:  * Given a new game with a stack of 1 "Chest" cards and 1 "Key" cards.
        // code: this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Chest", 1, "Key")
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenANewGameWithAStackOfNSCardsAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea("Woods Stroll Idea");
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSMayCreateASCard(String s1, String s2) {
        // text:  * The "Chest" may create a "Magic Book" card.
        // code: this.theSMayCreateASCard("Chest", "Magic Book")
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.theSMayCreateASCard


        throw new UnsupportedOperationException("The method theSMayCreateASCard(String s1, String s2) is not implemented yet.");
    }

    public void givenThatTheOddsAreThatWeWillGetASFromTheSCard(String s1, String s2) {
        // text:  * Given that the odds are that we will get a "Magic Component" from the "Chest" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Magic Component", "Chest")
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        throw new UnsupportedOperationException("The method givenThatTheOddsAreThatWeWillGetASFromTheSCard(String s1, String s2) is not implemented yet.");
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221207_PyramidStrollIdea_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

        throw new UnsupportedOperationException("The method endTheCurrentMoon() is not implemented yet.");
    }

    public void thereShouldBeNStacksOfNSCards(int expected, int n2, String s1) {
        // text:  * There should be 1 stacks of 1 "Magic Component" cards.
        // code: this.thereShouldBeNStacksOfNSCards(1, 1, "Magic Component")
        // hint: Post_20221205_Dothraki_Context.thereShouldBeNStacksOfNSCards

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);


        throw new UnsupportedOperationException("The method thereShouldBeNStacksOfNSCards(int expected, int n2, String s1) is not implemented yet.");
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
