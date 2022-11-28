package com.drpicox.game.food;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.CardListDTO;
import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.findAllCard;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221119_Meat_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GivenStackService givenStackService;
    private GameDTO gameDTO;

    Post_20221119_Meat_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewGameWithAStackOfNSAndNSCards(int n1, String s1, int n2, String s2) {
        // text:  * Given a new game with a stack of 4 "Villager" and 1 "Meat" cards
        // code: this.givenANewGameWithAStackOfNSAndNSCards(4, "Villager", 1, "Meat")
        // hint: Post_20221119_HowToKillAnimals_Context.givenANewGameWithAStackOfNSAndNSCards

        // Add here what is given
        givenStackService.givenStacks(1, byNames(n1,s1).and(n2,s2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSumOfAllSTagsValueShouldBeN(String s1, int expected) {
        // text:  * The sum of all "Eats" tags value should be 4.
        // code: this.theSumOfAllSTagsValueShouldBeN("Eats", 4)
        // hint: Post_20221120_Egg_Context.theSumOfAllSTagsValueShouldBeN

        var cards = CardListDTO.findAllCard(gameDTO);
        var sum = cards.stream().mapToInt(c -> c.getTag(s1)).sum();
        assertThat(sum).isEqualTo(expected);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221023_Barracks_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNSCards(int expected, String s1) {
        // text:  * There should be 4 "Villager" cards.
        // code: this.thereShouldBeNSCards(4, "Villager")
        // hint: Post_20221117_MilitiaKillCreeper_Context.thereShouldBeNSCards

        var cards = findAllCard(gameDTO, byName(s1));
        assertThat(cards).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
