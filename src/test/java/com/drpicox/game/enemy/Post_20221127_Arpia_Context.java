package com.drpicox.game.enemy;

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
public class Post_20221127_Arpia_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;
    private GameDTO gameDTO;

    Post_20221127_Arpia_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, RandomPickerServiceMock randomPickerServiceMock) {
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
        givenCardService.givenCards(2, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewGameWithAStackOfNSCardsAndNSCard(int n1, String s1, int n2, String s2) {
        // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Militia" card.
        // code: this.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Woods Stroll Idea", 1, "Militia")
        // hint: Post_20220913_Wolf_Context.givenANewGameWithAStackOfNSCardsAndNSCard

        // Add here what is given
        givenIdeaService.givenIdea("Woods Stroll Idea");
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String s1, int n1, int n2) {
        // text:  * Given there is the "Woods Stroll Idea" idea at level 5 and 0 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 5, 0)
        // hint: Post_20221129_Scorpion_Context.givenThereIsTheSIdeaAtLevelNAndNXp

        // Add here what is given
        givenIdeaService.givenIdea(s1, n1, n2);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSMayCreateASCard(String s1, String s2) {
        // text:  * The "Woods Stroll Idea" may create a "Arpia" card.
        // code: this.theSMayCreateASCard("Woods Stroll Idea", "Arpia")
        // hint: Post_20221103_Key_Context.theSMayCreateASCard

        var idea = getIdea(gameDTO, byName(s1));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(s2);
    }

    public void givenThatTheOddsAreThatWeWillFindASCardFromTheSCard(String s1, String s2) {
        // text:  * Given that the odds are that we will find a "Arpia" card from the "Woods Stroll Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillFindASCardFromTheSCard("Arpia", "Woods Stroll Idea")
        // hint: Post_20220913_Wolf_Context.givenThatTheOddsAreThatWeWillFindASCardFromTheSCard

        // Add here what is given
        randomPickerServiceMock.mockPick(s2, s1);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221103_Key_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSCardsAndNSCards(int expected, int n2, String s1, int n3, String s2, int n4, String s3) {
        // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Militia" cards and 1 "Arpia" cards
        // code: this.thereShouldBeNStacksOfNSNSCardsAndNSCards(1, 1, "Woods Stroll Idea", 1, "Militia", 1, "Arpia")
        // hint: Post_20220913_Wolf_Context.thereShouldBeNStacksOfNSNSCardsAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1).and(n3, s2).and(n4, s3)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
