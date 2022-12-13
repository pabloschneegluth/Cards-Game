package com.drpicox.game.building;

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
public class Post_20221109_BreedFish_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenStackService givenStackService;

    Post_20221109_BreedFish_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService) {
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

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
    }

    public void givenANewGame() {
        // text:  * Given a new game.
        // code: this.givenANewGame()

        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenAStackOfNSAndNSCards(int count1, String name1, int count2, String name2) {
        // text:  * Given a stack of 1 "Villager" and 1 "Fish Farm" cards.
        // code: this.givenAStackOfNSAndNSCards(1, "Villager", 1, "Fish Farm")

        // Add here what is given
        givenStackService.givenStacks(count1, byNames(count1, name1).and(count2, name2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221128_Grizzly_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeAStackOfNSNSAndNSCards(int expected, String s1, int n2, String s2, int n3, String s3) {
        // text:  * There should be a stack of 1 "Villager", 1 "Fish farm" and 1 "Fish" cards.
        // code: this.thereShouldBeAStackOfNSNSAndNSCards(1, "Villager", 1, "Fish farm", 1, "Fish")
        // hint: Post_20221019_Farm_Context.thereShouldBeNStackOfNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(expected, s1).and(n2, s2).and(n3, s3)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
