package com.drpicox.game.plant;

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
import com.drpicox.game.card.StackService;
import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.idea.GivenIdeaService;
import static com.drpicox.game.util.Names.byNames;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
import com.drpicox.game.card.api.StackListDTO;
import static com.drpicox.game.card.api.CardListDTO.findAllCard;

@Component
public class Post_20221020_PearTree_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;
    private final StackService stackService;

    Post_20221020_PearTree_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, RandomPickerServiceMock randomPickerServiceMock, StackService stackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
        this.randomPickerServiceMock = randomPickerServiceMock;
        this.stackService = stackService;
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

    public void givenANewGameWithAStackOfNSNSAndNSCards(int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * Given a new game with a stack of 1 "Harvest Idea", 1 "Villager", and 1 "Pear Tree" cards.
        // code: this.givenANewGameWithAStackOfNSNSAndNSCards(1, "Harvest Idea", 1, "Villager", 1, "Pear Tree")

        // Add here what is given
        givenIdeaService.givenIdea("Harvest Idea");
        givenStackService.givenStacks(1, byNames(n1, s1).and(n2, s2).and(n3, s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221020_WolfCallsWolf_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSNSAndNSCards(int expected, int n1, String s1, int n2, String s2, int n3, String s3, int n4, String s4) {
        // text:  * There should be 1 stacks of 1 "Harvest Idea", 1 "Villager", 1 "Pear Tree", and 2 "Pear" cards.
        // code: this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Pear Tree", 2, "Pear")
        // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSNSAndNSCards
        var stacks = StackListDTO.findAllStack(gameDTO,
        byNames(n1, s1).and(n2, s2).and(n3, s3).and(n4, s4)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void theSCardDescriptionShouldSaySIsS(String cardName, String term, String description) {
        // text:  * The "Pear Tree" card description should say "Fruit" is "Pear".
        // code: this.theSCardDescriptionShouldSaySIsS("Pear Tree", "Fruit", "Pear")
        // hint: Post_20220725_IdeasHaveLevels_Context.theSCardDescriptionShouldSaySIsS
        var card = findAllCard(gameDTO, byName(cardName)).get(0);
        assertThat(card.getDescriptionTerm(term)).isEqualTo(description);
    }

    public void givenANewGameWithAStackOfNS(int n1, String s1) throws IOException, URISyntaxException {
        // text:  * Given a new game with a stack of 5 "Berry".
        // code: this.givenANewGameWithAStackOfNS(5, "Berry")

        // Add here what is given
        givenGameService.givenGame("empty");
        givenStackService.givenStacks(1, byNames(n1, s1));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * Given there is the "Seed Idea" idea at level 2 and 0 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Seed Idea", 2, 0)
        // hint: Post_20221020_BuildIdea_Context.givenThereIsTheSIdeaAtLevelNAndNXp

        // Add here what is given
        givenIdeaService.givenIdea(ideaName, level, xp);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNStacksOfNSNSAndNSCards(int stackNum, int n1, String s1, int n2, String s2, int n3, String s3) {
        // text:  * Given there are 1 stacks of 1 "Seed Idea", 1 "Villager", and 1 "Pear" cards.
        // code: this.givenThereAreNStacksOfNSNSAndNSCards(1, 1, "Seed Idea", 1, "Villager", 1, "Pear")
        // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNStacksOfNSNSAndNSCards

        // Add here what is given
        givenStackService.givenStacks(stackNum, byNames(n1,s1).and(n2,s2).and(n3,s3));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNSCards(int n1, String s1) {
        // text:  * Given there are 0 "Pear Tree" cards.
        // code: this.givenThereAreNSCards(0, "Pear Tree")
        // hint: Post_20221020_WolfCallsWolf_Context.givenThereAreNSCards

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSCardShouldHaveNInSTag(String s1, int n1, String s2) {
        // text:  * The "Pear" card should have 1 in "Seed" tag.
        // code: this.theSCardShouldHaveNInSTag("Pear", 1, "Seed")
        // hint: Post_20220719_VillagersEatFood_Context.theSCardShouldHaveNInSTag
    }

    public void thereShouldBeNSCards(int expected, String s1) {
        // text:  * There should be 1 "Pear Tree" cards.
        // code: this.thereShouldBeNSCards(1, "Pear Tree")
        // hint: Post_20221020_WolfCallsWolf_Context.thereShouldBeNSCards

        var actual = expected; // FIXME
        assertThat(actual).isEqualTo(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
