package com.drpicox.game.location;

import com.drpicox.game.card.GivenStackService;
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

@Component
public class Post_20221207_Pyramid_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenStackService givenStackService;
    private final GivenIdeaService givenIdeaService;
    private final RandomPickerServiceMock randomPickerServiceMock;

    Post_20221207_Pyramid_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService, GivenIdeaService givenIdeaService, RandomPickerServiceMock randomPickerServiceMock) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
        this.givenIdeaService = givenIdeaService;
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

    public void givenANewGameWithAStackOfNSNSAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * Given a new game with a stack of 1 "Dessert Stroll Idea", 1 "Dessert" and 1 "Villager" cards.
        // code: this.givenANewGameWithAStackOfNSNSAndNSCards(1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager")
        // hint: Post_20221105_Flint_Context.givenANewGameWithAStackOfNSNSAndNSCards

        // Add here what is given
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2).and(n3,s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String s1, int n1, int n2) {
        // text:  * Given there is the "Dessert Stroll Idea" idea at level 4 and 0 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Dessert Stroll Idea", 4, 0)
        // hint: Post_20221122_Arrow_Context.givenThereIsTheSIdeaAtLevelNAndNXp

        // Add here what is given
        givenIdeaService.givenIdea(s1, n1, n2);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSMayCreateASCard(String s1, String s2) {
        // text:  * The "Dessert Stroll Idea" may create a "Pyramid" card.
        // code: this.theSMayCreateASCard("Dessert Stroll Idea", "Pyramid")
        // hint: Post_20221106_Lake_Context.theSMayCreateASCard

        var idea = getIdea(gameDTO, byName(s1));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(s2);
    }

    public void givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(String s1, String s2) {
        // text:  * Given that the odds are that we will get a "Pyramid" card from the "Dessert Stroll Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Pyramid", "Dessert Stroll Idea")
        // hint: Post_20221106_Lake_Context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard

        // Add here what is given
        randomPickerServiceMock.mockPick(s2, s1);

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221205_Arakh_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSNSAndNSCards(int expected, int n2, String s1, int n3, String s2, int n4, String s3, int n5, String s4) {
        // text:  * There should be 1 stacks of 1 "Dessert Stroll Idea", 1 "Dessert", 1 "Villager" and 1 "Pyramid" cards.
        // code: this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager", 1, "Pyramid")
        // hint: Post_20221129_FindingThingsInDessert_Context.thereShouldBeNStacksOfNSNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1).and(n3, s2).and(n4, s3).and(n5,s4)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
