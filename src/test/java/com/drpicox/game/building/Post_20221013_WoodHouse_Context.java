package com.drpicox.game.building;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.idea.GivenIdeaService;
import org.springframework.stereotype.Component;

import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221013_WoodHouse_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private GameDTO gameDTO;

    Post_20221013_WoodHouse_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenIdeaService givenIdeaService, GivenStackService givenStackService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        givenCardService.givenCards(1, "Berry");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewGameWithAStackOfNSCardsNSCardsAndNSCards(int n1, String ideaCard, int n2, String card1, int n3, String card2) {
        // text:  * Given a new game with a stack of 1 "Build Idea" cards, 1 "Villager" cards and 4 "Wood" cards
        // code: this.givenANewGameWithAStackOfNSCardsNSCardsAndNSCards(1, "Build Idea", 1, "Villager", 4, "Wood")
        // hint: Post_20221013_StoneHouse_Context.givenANewGameWithAStackOfNSCardsNSCardsAndNSCards

        // Add here what is given
        givenIdeaService.givenIdea("Build Idea");
        givenStackService.givenStacks(1, byNames(n1, ideaCard).and(n2, card1).and(n3, card2));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221024_Pickaxe_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }

    public void thereShouldBeNStacksOfNSCardsNSCardsAndNSCards(int expected, int numIdea, String idea, int numCard, String card, int numCard2, String card2) {
        // text:  * There should be 1 stacks of 1 "Build Idea" cards, 1 "Villager" cards and 1 "Wood House" cards
        // code: this.thereShouldBeNStacksOfNSCardsNSCardsAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Wood House")
        // hint: Post_20221013_StoneHouse_Context.thereShouldBeNStacksOfNSCardsNSCardsAndNSCards

        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(numIdea, idea).and(numCard, card).and(numCard2, card2));
        assertThat(stacks).hasSize(expected);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
