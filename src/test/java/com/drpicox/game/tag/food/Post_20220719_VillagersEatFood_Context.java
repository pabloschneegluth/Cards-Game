package com.drpicox.game.tag.food;

import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.findAllCard;
import static com.drpicox.game.card.api.CardListDTO.getCard;
import static com.drpicox.game.util.Names.byName;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20220719_VillagersEatFood_Context {

    private final FrontendSimulator frontendSimulator;
    private GameDTO gameDTO;

    Post_20220719_VillagersEatFood_Context(FrontendSimulator frontendSimulator) {
        this.frontendSimulator = frontendSimulator;
    }

    public void beforeTest() {
    }

    public void givenWeHaveEnteredIntoANewGame() {
        // example:  * Given we have entered into a new game.
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

    public void thereShouldBeNoSCard(String cardName) {
        // example:  * There is no "berry" card.
        // no = "berry"

        var matchingCards = findAllCard(gameDTO, byName(cardName));
        assertThat(matchingCards).isEmpty();
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

