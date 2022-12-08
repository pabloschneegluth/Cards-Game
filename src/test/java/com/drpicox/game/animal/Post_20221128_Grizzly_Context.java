package com.drpicox.game.animal;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.idea.GivenIdeaService;
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
public class Post_20221128_Grizzly_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;

    Post_20221128_Grizzly_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
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

    public void givenANewGameWithAStackOfNSNSNSCardsAndNS(int n1, String s1, int n2, String s2, int n3, String s3, int n4, String s4) {
        // text:  * Given a new game with a stack of 1 "Snowy Mountain Stroll Idea", 1 "Snowy Mountain", 1 "Villager" cards and 1 "Mountain Kit".
        // code: this.givenANewGameWithAStackOfNSNSNSCardsAndNS(1, "Snowy Mountain Stroll Idea", 1, "Snowy Mountain", 1, "Villager", 1, "Mountain Kit")

        // Add here what is given
        givenIdeaService.givenIdea("Snowy Mountain Stroll Idea");
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2).and(n3,s3).and(n4,s4));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * Given there is the "Snowy Mountain Stroll Idea" idea at level 1 and 0 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Snowy Mountain Stroll Idea", 1, 0)
        // hint: Post_20221122_Arrow_Context.givenThereIsTheSIdeaAtLevelNAndNXp

        // Add here what is given
        givenIdeaService.givenIdea(ideaName, level, xp);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSMayCreateASCard(String ideaName, String cardName) {
        // text:  * The "Snowy Mountain Stroll Idea" may create a "Grizzly" card.
        // code: this.theSMayCreateASCard("Snowy Mountain Stroll Idea", "Grizzly")
        // hint: Post_20221106_Lake_Context.theSMayCreateASCard

        var idea = getIdea(gameDTO, byName(ideaName));
        var rewards = idea.getCardRewards();

        assertThat(rewards).contains(cardName);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221122_Arrow_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSNSNSCardsAndNSCards(int expected, int n2, String s1, int n3, String s2, int n4, String s3, int n5, String s4, int n6, String s5) {
        // text:  * There should be 1 stacks of 1 "Snowy Mountain Stroll Idea", 1 "Snowy Mountain", 1 "Villager", 1 "Mountain Kit" cards and 1 "Grizzly" cards.
        // code: this.thereShouldBeNStacksOfNSNSNSNSCardsAndNSCards(1, 1, "Snowy Mountain Stroll Idea", 1, "Snowy Mountain", 1, "Villager", 1, "Mountain Kit", 1, "Grizzly")

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1).and(n3, s2).and(n4, s3).and(n5,s4).and(n6,s5)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
