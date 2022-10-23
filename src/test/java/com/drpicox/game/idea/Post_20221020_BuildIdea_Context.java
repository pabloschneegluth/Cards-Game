package com.drpicox.game.idea;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.idea.GivenIdeaService;
import com.drpicox.game.idea.api.IdeaListDTO;
import com.drpicox.game.util.RandomPickerServiceMock;
import org.springframework.stereotype.Component;

import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;

import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221020_BuildIdea_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;

    Post_20221020_BuildIdea_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, RandomPickerServiceMock randomPickerServiceMock) {
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
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * Given there is the "Harvest Idea" idea at level 2 and 9 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Harvest Idea", 2, 9)
        // hint: Post_20221013_Wood_Context.givenThereIsTheSIdeaAtLevelNAndNXp

        // Add here what is given
        givenIdeaService.givenIdea(ideaName, level, xp);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void thereShouldBeNoSIdea(String expected) {
        // text:  * There should be no "Build Idea" idea.
        // code: this.thereShouldBeNoSIdea("Build Idea")
        // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeNoSIdea

        var actual = IdeaListDTO.findAllIdea(gameDTO, byName(expected));
        assertThat(actual).isEmpty();
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221013_Wood_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void theSShouldHaveLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * The "Harvest Idea" should have level 3 and 0 XP.
        // code: this.theSShouldHaveLevelNAndNXp("Harvest Idea", 3, 0)
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.theSShouldHaveLevelNAndNXp
        var idea = getIdea(gameDTO, byName(ideaName));
        assertThat(idea.getLevel()).isEqualTo(level);
        assertThat(idea.getXp()).isEqualTo(xp);
    }

    public void thereShouldBeTheSIdea(String expected) {
        // text:  * There should be the "Build Idea" idea.
        // code: this.thereShouldBeTheSIdea("Build Idea")
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.thereShouldBeTheSIdea

        var actual = getIdea(gameDTO, byName(expected));
        assertThat(actual.getName()).isEqualTo(expected);

    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }

    public void givenANewGameWithAStackOfNSCardsNSCardAndNSCard(int n1, String s1, int n2, String s2, int n3, String s3) throws IOException, URISyntaxException {
        // givenCardService.givenCards(1, "Berry Bush");
        // givenCardService.givenCards(1, "Villager");
        // givenIdeaService.givenIdea("Harvest Idea");
        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        givenStackService.givenStacks(1, byNames("Harvest Idea", "Villager", "Berry Bush"));
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }
}
