package com.drpicox.game.plant;

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
import static com.drpicox.game.card.api.CardListDTO.findAllCard;


import java.io.IOException;
import java.net.URISyntaxException;

import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221020_PearTree_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final RandomPickerServiceMock randomPickerServiceMock;

    Post_20221020_PearTree_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, RandomPickerServiceMock randomPickerServiceMock) {
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
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
    }

    public void givenANewGame() {
        // text:  * Given a new game.
        // code: this.givenANewGame()
        // hint: Post_20220725_IdeasHaveLevels_Context.givenANewGame

        // Add here what is given

        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereIsTheSIdea(String s1) {
        // text:  * Given there is the "Harvest Idea" idea.
        // code: this.givenThereIsTheSIdea("Harvest Idea")
        // hint: Post_20220725_IdeasHaveLevels_Context.givenThereIsTheSIdea

        // Add here what is given
        givenIdeaService.givenIdea("Harvest Idea");
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNSCards(int n1, String s1) {
        // text:  * Given there are 1 "Pear" cards.
        // code: this.givenThereAreNSCards(1, "Pear")
        // hint: Post_20221020_WolfCallsWolf_Context.givenThereAreNSCards

        // Add here what is given
        givenCardService.givenCards(1,"Pear");
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNStacksOfNSNSAndNSCards(int n1, int n2, String idea, int n3, String card1, int n4, String card2) {
        // text:  * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Pear Tree" cards.
        // code: this.givenThereAreNStacksOfNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Pear Tree")
        // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNStacksOfNSNSAndNSCards

        // Add here what is given
        givenCardService.givenCards(1, "Villager");
        // And make sure that the game is in the right state (also for the frontend)
        givenStackService.givenStacks(1, byNames(n2, idea).and(n2, card1).and(n4, card2));
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221020_WolfCallsWolf_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSNSAndNSCards(int expected, int n1, String name1, int n2, String name2, int n3, String name3, int n4, String name4) {
        // text:  * There should be 1 stacks of 1 "Harvest Idea", 1 "Villager", 1 "Pear Tree", and 2 "Pear" cards.
        // code: this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Pear Tree", 2, "Pear")
        // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSNSAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n1, name1).and(n2, name2).and(n3, name3).and(n4, name4)
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

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String idea, int level, int xp) {
        givenIdeaService.givenIdea(idea, level, xp);

        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNStacksOfNSCards(int stack, int numCards, String cardName) {
        givenCardService.givenCards(5, cardName);
        givenStackService.givenStacks(1, byNames(5, cardName));
    }

    public void theSCardShouldHaveNInSTag(String cardName, int tagValue, String tagName) {
        var card = findAllCard(gameDTO, byName(cardName)).get(0);
        assertThat(card.getTag(tagName)).isEqualTo(tagValue);
    }

    public void thereShouldBeNSCards(int i, String string) {
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
