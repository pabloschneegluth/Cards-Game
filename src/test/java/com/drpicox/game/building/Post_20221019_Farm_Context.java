package com.drpicox.game.building;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.idea.GivenIdeaService;
import com.drpicox.game.util.RandomPickerServiceMock;
import org.springframework.stereotype.Component;

import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class Post_20221019_Farm_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;
    private final StackService stackService;

    Post_20221019_Farm_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, RandomPickerServiceMock randomPickerServiceMock, StackService stackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
        this.randomPickerServiceMock = randomPickerServiceMock;
        this.stackService = stackService;
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

    public void thereShouldBeNSCard(int expected, String s1) {
        // text:  * there should be 0 "Farm" card.
        // code: this.thereShouldBeNSCard(0, "Farm")
        // hint: Post_20221020_Pear_Context.thereShouldBeNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(s1)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void givenThereIsNStackOfNSAtLevelNAndNXpNSNSCards(int stackNum, int numS1, String s1, int level, int xp, int numS2, String s2, int numS3, String s3) {
        // text:  * given there is 1 stack of 1 "Build Idea", 1 "Stone", 2 "Wood" cards
        // code: this.givenThereIsNStackOfNSNSNSCards(1, 1, "Build Idea", 1, "Stone", 2, "Wood")

        // Add here what is given
        givenIdeaService.givenIdea("Build Idea", level, xp);
        givenStackService.givenStacks(1, byNames(numS1, s1).and(numS2, s2).and(numS3, s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

     }

    public void endTheCurrentMoon() {
        // text:  * end the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221020_Pear_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNStackOfNSNSAndNSCards(int expected, int numS1, String s1, int numS2, String s2, int numS3, String s3) {
        // text:  * there should be 1 stack of 0 "Stone", 0 "Wood" and 0 "Berry Bush" cards.
        // code: this.thereShouldBeNStackOfNSNSAndNSCards(1, 0, "Stone", 0, "Wood", 0, "Berry Bush")
        // hint: Post_20221020_Bone_Context.thereShouldBeNStacksOfNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(numS1, s1).and(numS2, s2).and(numS3, s3)
        );
        assertThat(stacks).hasSize(expected);

   }

    public void givenANewEmptyGame() throws IOException, URISyntaxException {
        // text:  * Given a new empty game
        // code: this.givenANewEmptyGame()

        // Add here what is given
        givenGameService.givenGame("empty");
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void givenThereIsNStackOfNSCardsNSCardsAndNSCardsOrAnotherPlant(int n1, int n2, String s1, int n3, String s2, int n4, String s3) {
        // text:  * Given there is 1 stack of 1 "Farm" cards, 1 "Villager" cards and 1 "Berry Bush" cards or another plant.
        // code: this.givenThereIsNStackOfNSCardsNSCardsAndNSCardsOrAnotherPlant(1, 1, "Farm", 1, "Villager", 1, "Berry Bush")

        // Add here what is given
        givenStackService.givenStacks(n1, byNames(n2, s1).and(n3,s2).and(n4,s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

     }

    public void thereShouldBeNStackOfNSCardsNSCardsNSCardsAndNSCards(int expected, int n2, String s1, int n3, String s2, int n4, String s3, int n5, String s4) {
        // text:  * There should be 1 stack of 5 "Berry" cards, 1 "Farm" cards, 1 "Villager" cards and 1 "Berry Bush" cards.
        // code: this.thereShouldBeNStackOfNSCardsNSCardsNSCardsAndNSCards(1, 5, "Berry", 1, "Farm", 1, "Villager", 1, "Berry Bush")

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1).and(n3, s2).and(n4, s3).and(n5, s4)
        );
        assertThat(stacks).hasSize(expected);


     }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
