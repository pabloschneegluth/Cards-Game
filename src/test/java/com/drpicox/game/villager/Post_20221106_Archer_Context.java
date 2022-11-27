package com.drpicox.game.villager;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import org.springframework.stereotype.Component;

import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221106_Archer_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;
    private GameDTO gameDTO;

    Post_20221106_Archer_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewGameWithAStackOfNSNSCards(int n1, String s1, int n2, String s2) {
        // text:  * Given a new game with a stack of 1 "Villager", 1 "Bow" cards.
        // code: this.givenANewGameWithAStackOfNSNSCards(1, "Villager", 1, "Bow")
        // hint: Post_20221105_Wizard_Context.givenANewGameWithAStackOfNSNSCards

        // Add here what is given
        givenStackService.givenStacks(1, byNames(n1,s1).and(n2,s2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221031_FishingRod_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSCards(int expected, int n2, String s1) {
        // text:  * There should be 1 stacks of 1 "Archer" cards.
        // code: this.thereShouldBeNStacksOfNSCards(1, 1, "Archer")
        // hint: Post_20221105_Wizard_Context.thereShouldBeNStacksOfNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
