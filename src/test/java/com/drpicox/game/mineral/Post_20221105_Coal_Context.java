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
import static com.drpicox.game.util.Names.byNames;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
@Component
public class Post_20221105_Coal_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;
    private final GivenIdeaService givenIdeaService;
    private final RandomPickerServiceMock randomPickerServiceMock;
    private GameDTO gameDTO;

    Post_20221105_Coal_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService,GivenStackService givenStackService,GivenIdeaService givenIdeaService,RandomPickerServiceMock randomPickerServiceMock) {
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
        givenCardService.givenCards(1, "Coal");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
    }

    public void givenANewGameWithNStackOfNSNSNSAndNSCards(int expected, int count1, String name1, int count2, String name2, int count3, String name3, int count4, String name4) {
        // text:  * Given a new game with 1 stack of 1 "Mine Stroll Idea", 1 "Mine", 1 "Villager" and 1 "Pickaxe" cards
        // code: this.givenANewGameWithNStackOfNSNSNSAndNSCards(1, 1, "Mine Stroll Idea", 1, "Mine", 1, "Villager", 1, "Pickaxe")
        // hint: Post_20221027_Sword_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea("Mine Stroll Idea");
        givenStackService.givenStacks(1, byNames(count1,name1).and(count2,name2).and(count3,name3).and(count4,name4));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        // text:  * The "Mine Stroll Idea" may create a "Stone" card.
        // code: this.theSMayCreateASCard("Mine Stroll Idea", "Stone")
        // hint: Post_20221106_Lake_Context.theSMayCreateASCard
        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(cardName);
    }

    public void givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(String s1, String s2) {
        // text:  * Given that the odds are that we will get a "Coal" card from the "Mine Stroll Idea" card.
        // code: this.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Coal", "Mine Stroll Idea")
        // hint: Post_20221106_Lake_Context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard

        // Add here what is given
        randomPickerServiceMock.mockPick(s2, s1);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNStacksOfNSNSNSNSAndNSCards(int expected, int count1, String name1, int count2, String name2, int count3, String name3, int count4, String name4, int count5, String name5) {
        // text:  * There should be 1 stacks of 1 "Mine Stroll Idea", 1 "Mine", 1 "Villager", 1 "Pickaxe" and 1 "Coal" cards
        // code: this.thereShouldBeNStacksOfNSNSNSNSAndNSCards(1, 1, "Mine Stroll Idea", 1, "Mine", 1, "Villager", 1, "Pickaxe", 1, "Coal")
        // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSNSAndNSCards


        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(count1, name1).and(count2, name2).and(count3, name3).and(count4,name4).and(count5,name5));
        assertThat(stacks).hasSize(expected);


    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
