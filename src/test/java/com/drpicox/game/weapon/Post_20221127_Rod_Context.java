package com.drpicox.game.weapon;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.card.api.StackListDTO;
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
public class Post_20221127_Rod_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final StackService stackService;

    Post_20221127_Rod_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, StackService stackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
        this.stackService = stackService;
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

    public void givenANewGameWithAStackOfNSNSNSAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3, int n4, String s4) {
        // text:  * Given a new game with a stack of 1 "Build Idea", 1 "Wizard", 3 "Wood" and 1 "Diamond" cards.
        // code: this.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Build Idea", 1, "Wizard", 3, "Wood", 1, "Diamond")
        // hint: Post_20221110_FindingThingsInLake_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea("Build Idea");
        givenStackService.givenStacks(1, byNames(n1,s1).and(n2,s2).and(n3,s3).and(n4,s4));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSNSCards(int expected, int n2, String s1, int n3, String s2, int n4, String s3) {
        // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Wizard", 1 "Rod" cards.
        // code: this.thereShouldBeNStacksOfNSNSNSCards(1, 1, "Build Idea", 1, "Wizard", 1, "Rod")
        // hint: Post_20221031_FishingRod_Context.thereShouldBeNStacksOfNSNSNSCards

        var stack = stackService.findAllStack();
        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1).and(n3, s2).and(n4, s3)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
