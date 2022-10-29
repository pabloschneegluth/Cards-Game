package com.drpicox.game.mineral;

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
public class Post_20221024_Diamond_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;
    private GameDTO gameDTO;

    Post_20221024_Diamond_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService,GivenIdeaService givenIdeaService,GivenStackService givenStackService,RandomPickerServiceMock randomPickerServiceMock) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService=givenIdeaService;
        this.givenStackService=givenStackService;
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
        // hint: Post_20221020_Bone_Context.givenANewGameWithAStackOfNSCardsAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea("Woods Stroll Idea");
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        // text:  * The "Woods Stroll Idea" may create a "Diamond" card.
        // code: this.theSMayCreateASCard("Woods Stroll Idea", "Diamond")
        // hint: Post_20221020_Bone_Context.theSMayCreateASCard

        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(cardName);

    }

    public void givenThatTheOddsAreThatWeWillGetASFromTheSCard(String cardName, String ideaName) {
        // text:  * Given that the odds are that we will get a "Diamond" from the "Woods Stroll Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Diamond", "Woods Stroll Idea")
        // hint: Post_20221020_Bone_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

        // Add here what is given
        randomPickerServiceMock.mockPick(ideaName, cardName);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221024_Pickaxe_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNStacksOfNSNSAndNSCards(int expected, int count1, String name1, int count2, String name2, int count3, String name3) {
        // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Diamond" cards.
        // code: this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Diamond")
        // hint: Post_20221020_Bone_Context.thereShouldBeNStacksOfNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(count1, name1).and(count2, name2).and(count3, name3)
        );
        assertThat(stacks).hasSize(expected);


    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
