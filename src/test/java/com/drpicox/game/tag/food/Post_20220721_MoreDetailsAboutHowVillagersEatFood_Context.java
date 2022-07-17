package com.drpicox.game.tag.food;

import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.api.CardListDTO;
import com.drpicox.game.game.GameFactory;
import com.drpicox.game.game.GameFactorySettings;
import com.drpicox.game.game.GameService;
import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.findAllCard;
import static com.drpicox.game.card.api.CardListDTO.getCard;
import static com.drpicox.game.util.Names.byName;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context {

    private final FrontendSimulator frontendSimulator;
    private final GameService gameService;
    private final GameFactory gameFactory;
    private final GivenCardService givenCardService;

    private GameDTO gameDTO;

    public Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context(FrontendSimulator frontendSimulator, GameService gameService, GameFactory gameFactory, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.gameService = gameService;
        this.gameFactory = gameFactory;
        this.givenCardService = givenCardService;
    }

    public void beforeTest() throws IOException, URISyntaxException {
        gameFactory.makeGame(new GameFactorySettings());
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNSAndNSCards(int count1, String name1, int count2, String name2) {
        // example:  * Given there are 2 "villager" and 2 "trader" cards.
        givenCardService.givenCards(count1, name1);
        givenCardService.givenCards(count2, name2);

        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSCardShouldHaveNInSTag(String cardName, int count, String tagName) {
        // example:  * The "villager" card should have 1 in "eats" tag.
        var matchingCard = getCard(gameDTO, byName(cardName));
        assertThat(matchingCard.getTag(tagName)).isEqualTo(count);
    }

    public void theSumOfAllSTagsValueShouldBeN(String tagName, int expected) {
        // example:  * The sum of all "eats" tags value should be 12.
        var cards = CardListDTO.findAllCard(gameDTO);
        var sum = cards.stream().mapToInt(c -> c.getTag(tagName)).sum();
        assertThat(sum).isEqualTo(expected);
    }

    public void endTheCurrentMoon() {
        // example:  * End the current moon.
        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNSCards(int expected, String cardName) {
        // example:  * There should be 2 "berry" cards.
        var actual = findAllCard(gameDTO, byName(cardName)).size();
        assertThat(actual).isEqualTo(expected);
    }

    public void afterTest() {
    }
}
