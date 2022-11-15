package com.drpicox.game.idea;

import com.drpicox.game.card.GivenStackService;
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
public class Post_20221115_LakeStrollIdea_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private GivenIdeaService givenIdeaService;
    private GivenStackService givenStackService;

    Post_20221115_LakeStrollIdea_Context(FrontendSimulator frontendSimulator,
                                         GivenGameService givenGameService,
                                         GivenCardService givenCardService,
                                         GivenIdeaService givenIdeaService,
                                         GivenStackService givenStackService) {
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

    public void givenANewGameWithAStackOfNSCardsAndNSCard(int n1, String s1, int n2, String s2) {
        // text:  * Given a new game with a stack of 1 "Lake" cards and 1 "Villager" card.
        // code: this.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Lake", 1, "Villager")
        // hint: Post_20221103_Creeper_Context.givenANewGameWithAStackOfNSCardsAndNSCard

        givenCardService.givenCards(1, "Lake");
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221103_Creeper_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeTheSIdea(String expected) {
        // text:  * There should be the "Lake Stroll Idea" idea.
        // code: this.thereShouldBeTheSIdea("Lake Stroll Idea")
        // hint: Post_20220723_Ideas_Context.thereShouldBeTheSIdea

        var actual = getIdea(gameDTO, byName(expected));
        assertThat(actual.getName()).isEqualTo(expected);

    }

    public void theSShouldHaveLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * The "Lake Stroll Idea" should have level 1 and 0 XP.
        // code: this.theSShouldHaveLevelNAndNXp("Lake Stroll Idea", 1, 0)
        // hint: Post_20221110_OldVillageStrollIdea_Context.theSShouldHaveLevelNAndNXp
        var idea = getIdea(gameDTO, byName(ideaName));
        assertThat(idea.getLevel()).isEqualTo(level);
        assertThat(idea.getXp()).isEqualTo(xp);

    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
