package com.drpicox.game.tag.food;

import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.GivenGameService;
import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.findAllCard;
import static com.drpicox.game.card.api.CardListDTO.getCard;
import static com.drpicox.game.util.Names.byName;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.api.GameDTO;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class Post_20220719_VillagersEatFood_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    public Post_20220719_VillagersEatFood_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
    }

    public void beforeTest() throws Throwable {
        // It seems redundant, but necessary because GamePage asks for it
        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewExample() throws Throwable {
        // NOTE: This method simulates beforeTest of a new test
        //       It is used to separate examples in the same test,
        //       which in your case should be different posts

        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNSCards(int count, String cardName) {
        givenCardService.givenCards(count, cardName);
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSCardShouldHaveNInSTag(String cardName, int count, String tagName) {
        // example:  * The "berry" card has 1 in "food" tag.
        // the = "berry"
        // has = 1
        // in = "food"
        var matchingCard = getCard(gameDTO, byName(cardName));
        assertThat(matchingCard.getTag(tagName)).isEqualTo(count);
    }

    public void endTheCurrentMoon() {
        // example:  * End the current moon.
        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNSCards(int expectedCount, String cardName) {
        // example:  * There is 1 "villager" card.
        // is = 1
        // n = "villager"

        var matchingCards = findAllCard(gameDTO, byName(cardName));
        assertThat(matchingCards).hasSize(expectedCount);
    }

    public void afterTest() {
    }
}

