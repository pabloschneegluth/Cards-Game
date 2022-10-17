package com.drpicox.game.mineral;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;

import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.util.RandomPickerServiceMock;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.idea.GivenIdeaService;
import static com.drpicox.game.util.Names.byNames;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;

@Component
public class Post_20221013_Iron_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;
    private final GivenIdeaService givenIdeaService;
    private final RandomPickerServiceMock randomPickerServiceMock;
    private GameDTO gameDTO;

    Post_20221013_Iron_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService,GivenStackService givenStackService,GivenIdeaService givenIdeaService, RandomPickerServiceMock randomPickerServiceMock) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService=givenStackService;
        this.givenIdeaService=givenIdeaService;
        this.randomPickerServiceMock=randomPickerServiceMock;
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

    public void givenANewGameWithAStackOfNSCardsAndNSCards(int count1, String name1, int count2, String name2) throws IOException, URISyntaxException {
        // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
        // code: this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenANewGameWithAStackOfNSCardsAndNSCards

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)

        givenIdeaService.givenIdea("Woods Stroll Idea");
        givenStackService.givenStacks(1, byNames(count1, name1).and(count2, name2));
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void givenThatTheOddsAreThatWeWillGetASFromTheSCard(String cardName, String ideaName) {
        // text:  * Given that the odds are that we will get a "Iron" from the "Woods Stroll Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Iron", "Woods Stroll Idea")
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

        // Add here what is given
        randomPickerServiceMock.mockPick(ideaName, cardName);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20220723_Ideas_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        // text:  * The "Woods Stroll Idea" may create a "Iron" card.
        // code: this.theSMayCreateASCard("Woods Stroll Idea", "Iron")
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.theSMayCreateASCard
        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(cardName);
    }

    public void thereShouldBeNStacksOfNSNSAndNSCards(int expected, int num1, String card1, int num2, String card2, int num3, String card3) {
        // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Iron" cards.
        // code: this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Iron")
        // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSAndNSCards
        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(num1, card1).and(num2, card2).and(num3, card3)
        );
        assertThat(stacks).hasSize(expected);


    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
