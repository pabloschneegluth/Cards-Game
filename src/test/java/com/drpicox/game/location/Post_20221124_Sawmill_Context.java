package com.drpicox.game.location;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.util.RandomPickerServiceMock;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.idea.GivenIdeaService;
import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;

@Component
public class Post_20221124_Sawmill_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;
    private final GivenIdeaService givenIdeaService;
    private final RandomPickerServiceMock randomPickerServiceMock;
    private GameDTO gameDTO;

    Post_20221124_Sawmill_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService,GivenStackService givenStackService, GivenIdeaService givenIdeaService, RandomPickerServiceMock randomPickerServiceMock) {
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

    public void givenANewGameWithAStackOfNSCardsAndNSCards(int n1, String s1, int n2, String s2) {
        // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
        // code: this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")
        // hint: Post_20221024_Diamond_Context.givenANewGameWithAStackOfNSCardsAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea("Woods Stroll Idea");
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * Given there is the "Woods Stroll Idea" idea at level 5 and 0 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 5, 0)
        // hint: Post_20220725_IdeasHaveLevels_Context.givenThereIsTheSIdeaAtLevelNAndNXp

        // Add here what is given
        givenIdeaService.givenIdea(ideaName, level, xp);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        // text:  * The "Woods Stroll Idea" may create a "Sawmill" card.
        // code: this.theSMayCreateASCard("Woods Stroll Idea", "Sawmill")
        // hint: Post_20221024_Diamond_Context.theSMayCreateASCard
        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(cardName);


    }

    public void givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(String s1, String s2) {
        // text:  * Given that the odds are that we will get a "Sawmill" card from the "Woods Stroll Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Sawmill", "Woods Stroll Idea")
        // hint: Post_20221106_Lake_Context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard

        // Add here what is given
        randomPickerServiceMock.mockPick(s2, s1);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20220725_IdeasHaveLevels_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNStacksOfNSNSAndNSCards(int expected, int count1, String name1, int count2, String name2, int count3, String name3) {
        // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Sawmill" cards.
        // code: this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Sawmill")
        // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeNStacksOfNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(count1, name1).and(count2, name2).and(count3, name3)
        );
        assertThat(stacks).hasSize(expected);

    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
