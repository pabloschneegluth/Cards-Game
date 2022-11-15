package com.drpicox.game.building;

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
public class Post_20221023_Barracks_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;
    private GameDTO gameDTO;

    Post_20221023_Barracks_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService,GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService=givenStackService;
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

    public void givenANewGameWithAStackOfNSCardsAndNSCardsAndNSCardsAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3, int n4, String s4) {
        // text:  * Given a new game with a stack of 1 "Villager" cards and 3 "Wood" cards and 3 "Stone" cards and 2 "Iron" cards.
        // code: this.givenANewGameWithAStackOfNSCardsAndNSCardsAndNSCardsAndNSCards(1, "Villager", 3, "Wood", 3, "Stone", 2, "Iron")

        // Add here what is given
       givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2).and(n3, s3).and(n4, s4));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221031_String_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNStacksOfNSCards(int expected, int n2, String s1) {
        // text:  * There should be 1 stacks of 1 "Barracks" cards
        // code: this.thereShouldBeNStacksOfNSCards(1, 1, "Barracks")
        // hint: Post_20221027_Militia_Context.thereShouldBeNStacksOfNSCards
        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
