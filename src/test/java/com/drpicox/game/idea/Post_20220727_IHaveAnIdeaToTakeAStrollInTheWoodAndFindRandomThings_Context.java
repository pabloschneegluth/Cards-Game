package com.drpicox.game.idea;

import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.idea.api.IdeaListDTO;
import com.drpicox.game.util.RandomPickerServiceMock;
import org.springframework.stereotype.Component;

import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;
    private GameDTO gameDTO;

    public Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, RandomPickerServiceMock randomPickerServiceMock) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
        this.randomPickerServiceMock = randomPickerServiceMock;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here, and change this contents, if necessary
        givenGameService.givenGame("default");
    }

    public void enterTheGame() {
        // text:  * Enter the game.
        // code: this.enterTheGame()
        // hint: Post_20220725_IdeasHaveLevels_Context.enterTheGame
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void thereShouldBeTheSIdea(String expected) {
        // text:  * There should be the "Harvest Idea" idea.
        // code: this.thereShouldBeTheSIdea("Harvest Idea")
        // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeTheSIdea

        var actual = getIdea(gameDTO, byName(expected));
        assertThat(actual.getName()).isEqualTo(expected);
    }

    public void theSShouldHaveLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * The "Harvest Idea" should have level 1 and 0 XP.
        // code: this.theSShouldHaveLevelNAndNXp("Harvest Idea", 1, 0)

        var idea = getIdea(gameDTO, byName(ideaName));
        assertThat(idea.getLevel()).isEqualTo(level);
        assertThat(idea.getXp()).isEqualTo(xp);
    }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        // text:  * The "Woods Stroll Idea" may create a "Berry" card.
        // code: this.theSMayCreateASCard("Woods Stroll Idea", "Berry")

        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(cardName);
    }

    public void theSIdeaShouldRequireNCardWithAtLeastNInSTag(String ideaName, int cardCount, int tagValue, String tagName) {
        // text:  * The "Woods Stroll Idea" idea should require 1 card with at least 1 in "Worker" tag.
        // code: this.theSIdeaShouldRequireNCardWithAtLeastNInSTag("Woods Stroll Idea", 1, 1, "Worker")
        // hint: Post_20220725_IdeasHaveLevels_Context.theSIdeaShouldRequireNCardWithAtLeastNInSTag

        var idea = IdeaListDTO.getIdea(gameDTO, byName(ideaName));
        var requirement = idea.findTagRequirement(tagName).get();
        assertThat(requirement.getCardCount()).isEqualTo(cardCount);
        assertThat(requirement.getTagValue()).isEqualTo(tagValue);
    }

    public void givenANewGameWithAStackOfNSCardsAndNSCards(int count1, String name1, int count2, String name2) throws IOException, URISyntaxException {
        // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
        // code: this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")

        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        givenIdeaService.givenIdea("Woods Stroll Idea");
        givenStackService.givenStacks(1, byNames(count1, name1).and(count2, name2));
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThatTheOddsAreThatWeWillGetASFromTheSCard(String cardName, String ideaName) {
        // text:  * Given that the odds are that we will get a "Berry" from the "Woods Stroll Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Berry", "Woods Stroll Idea")

        randomPickerServiceMock.mockPick(ideaName, cardName);
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20220725_IdeasHaveLevels_Context.endTheCurrentMoon
        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSAndNSCards(int expected, int count1, String name1, int count2, String name2, int count3, String name3) {
        // text:  * There should be 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
        // code: this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Berry Bush")
        // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(count1, name1).and(count2, name2).and(count3, name3)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * Given there is the "Harvest Idea" idea at level 1 and 9 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Harvest Idea", 1, 9)

        givenIdeaService.givenIdea(ideaName, level, xp);
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSMayNotCreateASCard(String ideaName, String cardName) {
        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();
        assertThat(rewards).doesNotContain(cardName);
    }
}
