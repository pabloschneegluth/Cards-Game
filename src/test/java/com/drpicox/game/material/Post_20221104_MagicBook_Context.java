package com.drpicox.game.material;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.idea.GivenIdeaService;
import com.drpicox.game.util.Names;
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
public class Post_20221104_MagicBook_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private GivenStackService givenStackService;
    private GivenIdeaService givenIdeaService;
    private RandomPickerServiceMock randomPickerServiceMock;

    Post_20221104_MagicBook_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService, GivenIdeaService givenIdeaService, RandomPickerServiceMock randomPickerServiceMock) {
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

    public void givenANewGameWithAStackOfNSCardNSCardNSAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3, int n4, String s4) {
        // text:  * Given a new game with a stack of 1 "Magic Component" card, 1 "Book" card, 1 "Build Idea", and 1 "Villager" cards.
        // code: this.givenANewGameWithAStackOfNSCardNSCardNSAndNSCards(1, "Magic Component", 1, "Book", 1, "Build Idea", 1, "Villager")
        // hint: Post_20221013_StoneHouse_Context.givenANewGameWithAStackOfNSCardsNSCardsAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea(s3);
        givenStackService.givenStacks(1, Names.byNames(n3, s3).and(n4, s4).and(n2, s2).and(n1, s1));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String s1, int n1, int n2) {
        // text:  * Given there is the "Build Idea" idea at level 4 and 0 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Build Idea", 4, 0)
        // hint: Post_20221106_Lake_Context.givenThereIsTheSIdeaAtLevelNAndNXp

        // Add here what is given
        givenIdeaService.givenIdea(s1, n1, n2);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

   }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        // text:  * The "Build Idea" may create a "Magic Book" card.
        // code: this.theSMayCreateASCard("Build Idea", "Magic Book")
        // hint: Post_20221106_Lake_Context.theSMayCreateASCard
        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(cardName);
     }

    public void givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(String card, String idea) {
        // text:  * Given that the odds are that we will get a "Magic Book" card from the "Build Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Magic Book", "Build Idea")
        // hint: Post_20221106_Lake_Context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard

        // Add here what is given
        randomPickerServiceMock.mockPick(idea, card);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221027_Sword_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNStacksOfNSNSAndNSCards(int expected, int n2, String s1, int n3, String s2, int n4, String s3) {
        // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Magic Book" cards.
        // code: this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Magic Book")
        // hint: Post_20221106_Lake_Context.thereShouldBeNStacksOfNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1).and(n3, s2).and(n4, s3)
        );
        assertThat(stacks).hasSize(expected);
   }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
