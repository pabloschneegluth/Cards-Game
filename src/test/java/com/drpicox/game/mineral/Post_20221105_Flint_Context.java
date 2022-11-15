package com.drpicox.game.mineral;

import com.drpicox.game.card.GivenStackService;
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
public class Post_20221105_Flint_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;
    private final GivenIdeaService givenIdeaService;
    private GameDTO gameDTO;

    Post_20221105_Flint_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService, GivenIdeaService givenIdeaService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
        this.givenIdeaService = givenIdeaService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewGameWithAStackOfNSNSAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * Given a new game with 1 stack of 1 "Villager", 1 "Stone" and 1 "Pickaxe" cards
        // code: this.givenANewGameWithNStackOfNSNSAndNSCards(1, 1, "Villager", 1, "Stone", 1, "Pickaxe")
        // hint: Post_20221027_Sword_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea("Build Idea");
        givenStackService.givenStacks(1, byNames(n1,s1).and(n2,s2).and(n3,s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
        System.out.println("N1: "+n1+"   S1: "+s1+"\nN2: "+"   S2: "+"\nN3: "+"   S3: ");
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon
        // code: this.endTheCurrentMoon()
        // hint: Post_20221103_Creeper_Context.endTheCurrentMoon
        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStackOfNSNSAndNSCards(int expected, int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * There should be 1 stack of 1 "Villager", 1 "Pickaxe" and 2 "Flint" cards
        // code: this.thereShouldBeNStackOfNSNSAndNSCards(1, 1, "Villager", 1, "Pickaxe", 2, "Flint")
        // hint: Post_20221019_Farm_Context.thereShouldBeNStackOfNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n1, s1).and(n2, s2).and(n3, s3)
        );
        System.out.println("N1: "+n1+"   S1: "+s1+"\nN2: "+"   S2: "+"\nN3: "+"   S3: ");
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
