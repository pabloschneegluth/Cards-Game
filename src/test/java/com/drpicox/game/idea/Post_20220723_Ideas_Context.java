package com.drpicox.game.idea;

import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.MoveCardDTO;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.game.GameFactory;
import com.drpicox.game.game.GameFactorySettings;
import com.drpicox.game.game.api.GameDTO;
import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.*;
import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;

@Component
public class Post_20220723_Ideas_Context {

    private final FrontendSimulator frontendSimulator;
    private final GameFactory gameFactory;
    private final GivenIdeaService givenIdeaService;
    private final GivenCardService givenCardService;
    private final GivenStackService givenStackService;
    private final GivenGameService givenGameService;
    private GameDTO gameDTO;

    public Post_20220723_Ideas_Context(FrontendSimulator frontendSimulator, GameFactory gameFactory, GivenIdeaService givenIdeaService, GivenCardService givenCardService, GivenStackService givenStackService, GivenGameService givenGameService) {
        this.frontendSimulator = frontendSimulator;
        this.gameFactory = gameFactory;
        this.givenIdeaService = givenIdeaService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
        this.givenGameService = givenGameService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here, and change this contents, if necessary
        gameFactory.makeGame(new GameFactorySettings());
    }

    public void enterTheGame() {
        // text:  * Enter the game.
        // code: this.enterTheGame()
        // hint: Post_20220717_BushesVillagersAndBerries_Context.enterTheGame
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void thereShouldBeTheSIdea(String expected) {
        // text:  * There should be the "Harvest Idea" idea.
        // code: this.thereShouldBeTheSIdea("Harvest Idea")

        var actual = getIdea(gameDTO, byName(expected));
        assertThat(actual.getName()).isEqualTo(expected);
    }

    public void thereShouldBeNSCards(int expected, String cardName) {
        // text:  * There should be 0 "Harvest Idea" cards.
        // code: this.thereShouldBeNSCards(0, "Harvest Idea")
        // hint: Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context.thereShouldBeNSCards

        var actual = findAllCard(gameDTO, byName(cardName)).size();
        assertThat(actual).isEqualTo(expected);
    }

    public void drawACardFromTheSIdea(String ideaName) {
        // text:  * Draw a card from the "Harvest Idea" idea.
        // code: this.drawACardFromTheSIdea("Harvest Idea")

        var idea = getIdea(gameDTO, byName(ideaName));
        gameDTO = frontendSimulator.post("/api/v1/game/ideas/"+idea.getId()+"/draw", null, GameDTO.class);
    }

    public void givenThereIsTheSIdea(String ideaName) {
        // text:  * Given there is the "Harvest Idea" idea.
        // code: this.givenThereIsTheSIdea("Harvest Idea")

        givenIdeaService.givenIdea(ideaName);
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNSCards(int count, String cardName) {
        // text:  * Given there are 1 "Harvest Idea" cards.
        // code: this.givenThereAreNSCards(1, "Harvest Idea")

        givenCardService.givenCards(count, cardName);
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void discardNSCards(int count, String cardName) {
        // text:  * Discard 1 "Harvest Idea" cards.
        // code: this.discardNSCards(1, "Harvest Idea")

        var cards = findAllCard(gameDTO, byName(cardName));
        var discards = cards.subList(0, count);
        for (var card : discards) {
            gameDTO = frontendSimulator.post("/api/v1/game/cards/"+card.getId()+"/discard", null, GameDTO.class);
        }
    }


    public void theSCardShouldHaveNInSTag(String cardName, int value, String tagName) {
        // * The "Villager" card should have 1 in "Fruit Plant" tag.
        var matchingCard = getCard(gameDTO, byName(cardName));
        assertThat(matchingCard.getTag(tagName)).isEqualTo(value);
    }


    public void theSCardDescriptionShouldSaySIsS(String cardName, String term, String text) {
        // * The "Berry Bush" card description should say "Fruit" is "Berry".
        var matchingCard = getCard(gameDTO, byName(cardName));
        assertThat(matchingCard.getDescriptionTerm(term)).contains(text);
    }

    public void theSIdeaShouldRequireNCardWithAtLeastNInSTag(String ideaName, int cardCount, int tagValue, String tagName) {
        // * The "Harvest Idea" idea should require 1 card with at least 1 in "Fruit Plant" tag.
        // theSIdeaShouldRequireNCardWithAtLeastNInSTag("Harvest Idea", 1, 1, "Fruit Plant");

        var idea = getIdea(gameDTO, byName(ideaName));
        var requirement = idea.findTagRequirement(tagName).get();
        assertThat(requirement.getCardCount()).isEqualTo(cardCount);
        assertThat(requirement.getTagValue()).isEqualTo(tagValue);
    }

    public void givenANewGame() throws Throwable {
        // text:  * Given a new game.
        // code: this.givenANewGame()

        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void moveTheSCardToItsOwnStack(String cardName) {
        var card = getCard(gameDTO, byName(cardName));
        var cardId = card.getId();
        var position = StackListDTO.getFreePosition(gameDTO);
        var zindex = 0;

        gameDTO = frontendSimulator.post("/api/v1/game/cards/"+cardId+"/move", new MoveCardDTO(position, zindex), GameDTO.class);
    }

    public void moveTheSCardOnTopOfTheSCard(String sourceCardName, String targetCardName) {
        // text:  * Move the "Villager" card on top of the "Harvest Idea" card.
        // code: this.moveTheSCardOnTopOfTheSCard("Villager", "Harvest Idea")

        var targetCard = getCard(gameDTO, byName(targetCardName));
        var position = targetCard.getPosition();
        var zindex = targetCard.getZindex();

        var card = getCard(gameDTO, byName(sourceCardName));
        var cardId = card.getId();

        gameDTO = frontendSimulator.post("/api/v1/game/cards/"+cardId+"/move", new MoveCardDTO(position, zindex + 1), GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSNSAndNSCards(int expected, int count1, String name1, int count2, String name2, int count3, String name3) {
        // text:  * There should be 1 stack of 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
        // code: this.thereShouldBeNStackOfNSNSAndNSCards(1, 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

        thereShouldBeNStacksOfNSNSNSAndNSCards(expected, count1, name1, count2, name2, count3, name3, 0, null);
    }

    public void thereShouldBeNStacksOfNSNSNSAndNSCards(int expected, int count1, String name1, int count2, String name2, int count3, String name3, int count4, String name4) {
        // text:  * There should be 1 stack of 1 "Berry Bush", 1 "Villager", 1 "Harvest Idea", and 1 "Villager" cards.
        // code: this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea", 1, "Villager")

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(count1, name1).and(count2, name2).and(count3, name3).and(count4, name4)
        );
        assertThat(stacks).hasSize(expected);
    }


    public void givenThereAreNStacksOfNSNSAndNSCards(int count, int count1, String name1, int count2, String name2, int count3, String name3) {
        // text:  * Given there are 1 stacks of 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
        // code: this.givenThereAreNStacksOfNSNSAndNSCards(1, 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

        givenStackService.givenStacks(count, byNames(count1, name1).and(count2, name2).and(count3, name3));
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void givenThereAreNStacksOfNSNSNSAndNSCards(int count, int count1, String name1, int count2, String name2, int count3, String name3, int count4, String name4) {
        // text:  * Given there are 1 stacks of 1 "Corpse", 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
        // code: this.givenThereAreNStacksOfNSNSNSAndNSCards(1, 1, "Corpse", 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

        givenStackService.givenStacks(count, byNames(count1, name1).and(count2, name2).and(count3, name3).and(count4, name4));
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
