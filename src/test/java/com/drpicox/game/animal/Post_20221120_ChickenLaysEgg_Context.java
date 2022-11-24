package com.drpicox.game.animal;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.game.api.GameDTO;
import static com.drpicox.game.util.Names.byNames;

@Component
public class Post_20221120_ChickenLaysEgg_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;

    private GameDTO gameDTO;

    Post_20221120_ChickenLaysEgg_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService,GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService=givenStackService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
    }

    public void givenThereAreNSCards(int count, String cardName) {
        // text:  * Given there are 1 "Chicken" cards.
        // code: this.givenThereAreNSCards(1, "Chicken")
        // hint: Post_20220723_Ideas_Context.givenThereAreNSCards

        // Add here what is given
        givenCardService.givenCards(count, cardName);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSAndNSCards(int expected, int count1, String name1, int count2, String name2) {
        // text:  * There should be 1 stacks of 1 "Chicken" and 1 "Egg" cards.
        // code: this.thereShouldBeNStacksOfNSAndNSCards(1, 1, "Chicken", 1, "Egg")
        // hint: Post_20221104_Paper_Context.thereShouldBeNStacksOfNSNSAndNSCards


        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(count1, name1).and(count2, name2)
        );
        assertThat(stacks).hasSize(expected);


    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
