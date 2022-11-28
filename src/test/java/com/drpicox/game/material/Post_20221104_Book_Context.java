package com.drpicox.game.material;

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
public class Post_20221104_Book_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;
    private final GivenIdeaService givenIdeaService;
    private StackService stackService;
    private GameDTO gameDTO;

    Post_20221104_Book_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService, GivenIdeaService givenIdeaService, StackService stackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
        this.givenIdeaService = givenIdeaService;
        this.stackService = stackService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewGameWithAStackOfNSNSCardsAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * Given a new game with a stack of 5 "Paper" cards, 1 "Build Idea" and 1 "Villager" cards.
        // code: this.givenANewGameWithAStackOfNSCardsNSAndNSCards(5, "Paper", 1, "Build Idea", 1, "Villager")
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenANewGameWithAStackOfNSCardsAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea("Build Idea");
        givenStackService.givenStacks(1, byNames(n1,s1).and(n2,s2).and(n3,s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String s1, int n1, int n2) {
        // text:  * Given there is the "Build Idea" idea at level 3 and 0 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Build Idea", 3, 0)
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenThereIsTheSIdeaAtLevelNAndNXp

        // Add here what is given
        givenIdeaService.givenIdea("Build Idea", n1, n2);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSAndNSCards(int expected, int n2, String s1, int n3, String s2, int n4, String s3) {
        // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Book" cards.
        // code: this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Book")
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.thereShouldBeNStacksOfNSNSAndNSCards

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
