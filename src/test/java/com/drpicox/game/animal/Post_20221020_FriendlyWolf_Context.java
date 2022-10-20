package com.drpicox.game.animal;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.util.RandomPickerServiceMock;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.idea.GivenIdeaService;
import static com.drpicox.game.util.Names.byNames;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
import com.drpicox.game.card.api.StackListDTO;

@Component
public class Post_20221020_FriendlyWolf_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;

    Post_20221020_FriendlyWolf_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, RandomPickerServiceMock randomPickerServiceMock) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
        this.randomPickerServiceMock = randomPickerServiceMock;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
    }

    public void givenAStackOfNSNSAndNS(int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * Given a stack of 1 "Wolf", 1 "Villager" and 1 "Bone".
        // code: this.givenAStackOfNSNSAndNS(1, "Wolf", 1, "Villager", 1, "Bone")

        // Add here what is given
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2).and(n3,s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThatTheOddsAreThatWeWillGetASFromTheSCard(String s1, String s2) {
        // text:  * Given that the odds are that we will get a "Friendly Wolf" from the "Wolf" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Friendly Wolf", "Wolf")
        // hint: Post_20221013_Iron_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221013_Iron_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNSCard(int expected, String s1) {
        // text:  * There should be 0 "Wolf" card.
        // code: this.thereShouldBeNSCard(0, "Wolf")
        // hint: Post_20220723_Ideas_Context.thereShouldBeNSCards

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);

    }

    public void thereShouldBeNStackOfNSAndNS(int expected, int n2, String s1, int n3, String s2) {
        // text:  * There should be 1 stack of 1 "Villager" and 1 "Friendly Wolf"
        // code: this.thereShouldBeNStackOfNSAndNS(1, 1, "Villager", 1, "Friendly Wolf")

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
