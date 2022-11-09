package com.drpicox.game.food;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.api.CardListDTO;
import com.drpicox.game.game.api.GameDTO;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.card.api.CardListDTO.findAllCard;

@Component
public class Post_20221106_Fish_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    Post_20221106_Fish_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
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

    public void givenThereAreNSCards(int count, String cardName) {
        // text:  * Given there are 1 "Fish" cards.
        // code: this.givenThereAreNSCards(1, "Fish")
        // hint: Post_20221020_WolfCallsWolf_Context.givenThereAreNSCards

        // Add here what is given
        givenCardService.givenCards(count, cardName);
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void theSumOfAllSTagsValueShouldBeN(String tagName, int expected) {
        // text:  * The sum of all "Eats" tags value should be 3.
        // code: this.theSumOfAllSTagsValueShouldBeN("Eats", 3)
        // hint: Post_20221020_Pear_Context.theSumOfAllSTagsValueShouldBeN
        var cards = CardListDTO.findAllCard(gameDTO);
        var sum = cards.stream().mapToInt(c -> c.getTag(tagName)).sum();
        assertThat(sum).isEqualTo(expected);
        }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNSCards(int expected, String cardName) {
        // text:  * There should be 3 "Villager" cards.
        // code: this.thereShouldBeNSCards(3, "Villager")
        // hint: Post_20221020_WolfCallsWolf_Context.thereShouldBeNSCards

        var matchingCards = findAllCard(gameDTO, byName(cardName));
        assertThat(matchingCards).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
