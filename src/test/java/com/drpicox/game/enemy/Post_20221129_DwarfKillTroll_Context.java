package com.drpicox.game.enemy;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.util.Names;
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
public class Post_20221129_DwarfKillTroll_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private final GivenStackService givenStackService;

    Post_20221129_DwarfKillTroll_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
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

    public void givenThereAreNSCards(int n1, String s1) {
        // text:  * Given there are 1 "Dwarf" cards.
        // code: this.givenThereAreNSCards(1, "Dwarf")
        // hint: Post_20221207_DothrakiKillMummy_Context.givenThereAreNSCards

        // Add here what is given
        givenStackService.givenStacks(1, Names.byNames(n1,s1));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221103_Key_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNSCards(int expected, String s1) {
        // text:  * There should be 0 "Troll" cards.
        // code: this.thereShouldBeNSCards(0, "Troll")
        // hint: Post_20221207_DothrakiKillMummy_Context.thereShouldBeNSCards

        var cards = findAllCard(gameDTO, byName(s1));
        assertThat(cards).hasSize(expected);

    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
