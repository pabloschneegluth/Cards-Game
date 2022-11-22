package com.drpicox.game.food;

import com.drpicox.game.card.api.CardListDTO;
import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.findAllCard;
import static com.drpicox.game.util.Names.byName;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221120_Egg_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    Post_20221120_Egg_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
    }

    public void givenThereAreNSCards(int count, String cardName) {
        // text:  * Given there are 1 "Egg" cards.
        // code: this.givenThereAreNSCards(1, "Egg")
        // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNSCards

        // Add here what is given
        givenCardService.givenCards(count, cardName);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSumOfAllSTagsValueShouldBeN(String tagName, int expected) {
        // text:  * The sum of all "Eats" tags value should be 1.
        // code: this.theSumOfAllSTagsValueShouldBeN("Eats", 1)
        // hint: Post_20221106_Fish_Context.theSumOfAllSTagsValueShouldBeN
        var cards = CardListDTO.findAllCard(gameDTO);
        var sum = cards.stream().mapToInt(c -> c.getTag(tagName)).sum();
        assertThat(sum).isEqualTo(expected);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221027_Sword_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNSCards(int expected, String cardName) {
        // text:  * There should be 1 "Villager" cards.
        // code: this.thereShouldBeNSCards(1, "Villager")
        // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeNSCards

        var matchingCards = findAllCard(gameDTO, byName(cardName));
        assertThat(matchingCards).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
