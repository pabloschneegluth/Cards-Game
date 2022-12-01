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
public class Post_20221110_FindingThingsInLake_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private GivenIdeaService givenIdeaService;
    private GivenStackService givenStackService;
    private RandomPickerServiceMock randomPickerServiceMock;

    Post_20221110_FindingThingsInLake_Context(FrontendSimulator frontendSimulator,
                                              GivenGameService givenGameService,
                                              GivenCardService givenCardService,
                                              GivenIdeaService givenIdeaService,
                                              GivenStackService givenStackService,
                                              RandomPickerServiceMock randomPickerServiceMock) {
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

    public void givenANewGameWithAStackOfNSNSNSAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3, int n4, String s4) {
        // text:  * Given a new game with a stack of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager" and 1 "Fishing Rod" cards.
        // code: this.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Lake Stroll Idea", 1, "Lake", 1, "Villager", 1, "Fishing Rod")
        // hint: Post_20221122_Hammer_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea("Lake Stroll Idea");
        givenStackService.givenStacks(1, byNames(n1,s1).and(n2,s2).and(n3,s3).and(n4,s4));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        // text:  * The "Lake Stroll Idea" may create a "Fish" card.
        // code: this.theSMayCreateASCard("Lake Stroll Idea", "Fish")
        // hint: Post_20221114_Chicken_Context.theSMayCreateASCard

        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(cardName);

    }

    public void givenThatTheOddsAreThatWeWillGetASFromTheSCard(String cardName, String ideaName) {
        // text:  * Given that the odds are that we will get a "Fish" from the "Lake" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Fish", "Lake")
        // hint: Post_20221114_Chicken_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

        // Add here what is given
        randomPickerServiceMock.mockPick(ideaName, cardName);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNStacksOfNSNSNSNSAndNSCards(int expected, int count1, String name1,
                                                         int count2, String name2,
                                                         int count3, String name3,
                                                         int count4, String name4,
                                                         int count5, String name5) {
        // text:  * There should be 1 stacks of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager", 1 "Fishing Rod" and 1 "Fish" cards.
        // code: this.thereShouldBeNStacksOfNSNSNSNSAndNSCards(1, 1, "Lake Stroll Idea", 1, "Lake", 1, "Villager", 1, "Fishing Rod", 1, "Fish")
        // hint: Post_20221105_Coal_Context.thereShouldBeNStacksOfNSNSNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(count1, name1).and(count2, name2).and(count3, name3).and(count4,name4).and(count5,name5));
        assertThat(stacks).hasSize(expected);

    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
