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
public class Post_20221129_FindingThingsInDessert_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;

    Post_20221129_FindingThingsInDessert_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, RandomPickerServiceMock randomPickerServiceMock) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
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

    public void givenANewGameWithAStackOfNSNSAndNS(int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * Given a new game with a stack of 1 "Dessert Stroll Idea", 1 "Dessert" and 1 "Villager".
        // code: this.givenANewGameWithAStackOfNSNSAndNS(1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager")
        // hint: Post_20221128_MountainKit_Context.givenANewGameWithAStackOfNSNSNSAndNS

        // Add here what is given
        givenIdeaService.givenIdea("Dessert Stroll Idea",5,0);
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2).and(n3,s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        // text:  * The "Dessert Stroll Idea" may create a "Scorpion" card.
        // code: this.theSMayCreateASCard("Dessert Stroll Idea", "Scorpion")
        // hint: Post_20221105_Coal_Context.theSMayCreateASCard

        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();

        assertThat(rewards).contains(cardName);

    }

    public void givenThatTheOddsAreThatWeWillGetASFromTheSCard(String cardName, String ideaName) {
        // text:  * Given that the odds are that we will get a "Scorpion" from the "Dessert Stroll Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Scorpion", "Dessert Stroll Idea")
        // hint: Post_20221024_Diamond_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

        // Add here what is given
        randomPickerServiceMock.mockPick(ideaName, cardName);

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221127_Rod_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSNSAndNSCards(int expected, int n2, String s1, int n3, String s2, int n4, String s3, int n5, String s4) {
        // text:  * There should be 1 stacks of 1 "Dessert Stroll Idea", 1 "Dessert", 1 "Villager" and 1 "Scorpion" cards.
        // code: this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager", 1, "Scorpion")
        // hint: Post_20221124_FindingThingsInSawmill_Context.thereShouldBeNStacksOfNSNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1).and(n3, s2).and(n4, s3).and(n5,s4));
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
