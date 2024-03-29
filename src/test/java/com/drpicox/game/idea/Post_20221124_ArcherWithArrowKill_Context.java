package com.drpicox.game.idea;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.game.api.GameDTO;
import static com.drpicox.game.util.Names.byNames;
@Component
public class Post_20221124_ArcherWithArrowKill_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;

    private GameDTO gameDTO;

    public Post_20221124_ArcherWithArrowKill_Context(FrontendSimulator frontendSimulator,
            GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
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

    public void givenANewGameWithAStackOfNSNSAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * Given a new game with a stack of 1 "Archer", 1 "Arrow" and 1 "Wolf" cards.
        // code: this.givenANewGameWithAStackOfNSNSAndNSCards(1, "Archer", 1, "Arrow", 1, "Wolf")
        // hint: Post_20221105_Flint_Context.givenANewGameWithAStackOfNSNSAndNSCards

        // Add here what is given
        givenStackService.givenStacks(1, byNames(n1,s1).and(n2,s2).and(n3,s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeAStackWithNSNSAndNSCards(int count1, String name1, int count2, String name2, int count3, String name3) {
        // text:  * There should be a stack with 1 "Archer", 0 "Arrow" and 0 "Wolf" cards.
        // code: this.thereShouldBeAStackWithNSNSAndNSCards(1, "Archer", 0, "Arrow", 0, "Wolf")
        // hint: Post_20221119_HowToKillAnimals_Context.thereShouldBeAStackWithNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(count1,name1).and(count2, name2).and(count3, name3)
        );
        assertThat(stacks).hasSize(1);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
