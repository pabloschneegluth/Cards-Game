package com.drpicox.game.enemy;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.util.RandomPickerServiceMock;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.idea.GivenIdeaService;
import static com.drpicox.game.util.Names.byNames;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
import com.drpicox.game.card.api.StackListDTO;

@Component
public class Post_20220913_Wolf_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;

    Post_20220913_Wolf_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, RandomPickerServiceMock randomPickerServiceMock) {
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

    public void givenANewGameWithAStackOfNSCardsAndNSCard(int n1, String s1, int n2, String s2) {
        // text:  * Given a new game with a stack "Woods Stroll Idea " cards and 1 "Militia" card.
        // code: this.givenANewGameWithAStackSCardsAndNSCard("Woods Stroll Idea ", 1, "Militia")

        // Add here what is given
        givenIdeaService.givenIdea("Woods Stroll Idea");
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThatTheOddsAreThatWeWillFindASCardFromTheSCard(String cardName, String ideaName) {
        // text:  * Given that the odds are that we will find a "Wolf" card from the "Woods Stroll Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillFindASCardFromTheSCard("Wolf", "Woods Stroll Idea")

        // Add here what is given
        randomPickerServiceMock.mockPick(ideaName, cardName);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221013_Wood_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSAndNSCards(int expected, int n2, String s1, int n3, String s2) {
        // text:  * There should be 1 stacks of 1 "Woods Stroll Idea" and 1 "Militia" cards
        // code: this.thereShouldBeNStacksOfNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Militia")
        // hint: Post_20221013_Wood_Context.thereShouldBeNStacksOfNSNSAndNSCards

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();

        assertThat(rewards).contains(cardName);
    }

    public void thereShouldBeNStacksOfNSNSCardsAndNSCards(int stackNum, int n1, String s1, int n2, String s2, int n3,
                                                          String s3) {
        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n1, s1).and(n2, s2).and(n3, s3)
        );
        assertThat(stacks).hasSize(stackNum);
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String ideaName, int level, int xp) {
        givenIdeaService.givenIdea(ideaName, level, xp);

        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }
}
