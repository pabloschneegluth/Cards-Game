package com.drpicox.game.idea;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.idea.api.IdeaListDTO;
import com.drpicox.game.util.Names;
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
public class Post_20221124_MagicIdea_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private GivenStackService givenStackService;
    private GivenIdeaService givenIdeaService;

    Post_20221124_MagicIdea_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService, GivenIdeaService givenIdeaService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
        this.givenIdeaService = givenIdeaService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(2, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
    }

    public void givenANewGameWithAStackOfNSCard(int n1, String s1) {
        // text:  * Given a new game with a stack of 1 "Wizard" card.
        // code: this.givenANewGameWithAStackOfNSCard(1, "Wizard")
        // hint: Post_20221106_Archer_Context.givenANewGameWithAStackOfNSNSCards
        givenIdeaService.givenIdea("Old Village Stroll Idea");
        // Add here what is given
        givenStackService.givenStacks(1, Names.byNames(1,"Old Village Stroll Idea").and(n1,s1));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221031_FishingRod_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeTheSIdea(String expected) {
        // text:  * There should be the "Old Village Stroll Idea" idea.
        // code: this.thereShouldBeTheSIdea("Old Village Stroll Idea")
        // hint: Post_20221115_LakeStrollIdea_Context.thereShouldBeTheSIdea

        var stacks = IdeaListDTO.getIdea(gameDTO,
            name -> name.getName().equalsIgnoreCase("Old Village Stroll Idea")
        );
        assertThat(stacks.getName()).isEqualTo(expected);

    }

    public void theSShouldHaveLevelNAndNXp(String s1, int n1, int n2) {
        // text:  * The "Old Village Stroll Idea" should have level 1 and 0 XP.
        // code: this.theSShouldHaveLevelNAndNXp("Old Village Stroll Idea", 1, 0)
        // hint: Post_20221115_LakeStrollIdea_Context.theSShouldHaveLevelNAndNXp
        var actual = getIdea(gameDTO, byName(s1));
        assertThat(actual.getName()).isEqualTo(s1);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
