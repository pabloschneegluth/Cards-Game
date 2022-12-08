package com.drpicox.game.spell;

import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.CardListDTO;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.idea.GivenIdeaService;
import com.drpicox.game.util.Names;
import org.springframework.stereotype.Component;

import static com.drpicox.game.idea.api.IdeaListDTO.getIdea;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221124_SpellToCreateAGolem_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;
    private GivenStackService givenStackService;
    private GivenIdeaService givenIdeaService;

    Post_20221124_SpellToCreateAGolem_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService, GivenStackService givenStackService, GivenIdeaService givenIdeaService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
        this.givenStackService = givenStackService;
        this.givenIdeaService = givenIdeaService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

        // Please, verify that:
        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?
        // [ ] is the empty game right for this post?
   }

    public void givenANewGameWithAStackOfNSNSNSNSNS(int n1, String s1, int n2, String s2, int n3, String s3, int n4, String s4, int n5, String s5) {
        // text:  * Given a new game with a stack of 1 "Magic Idea", 1 "Wizard", 5 "Bone", 3 "Iron", 1"Gold".
        // code: this.givenANewGameWithAStackOfNSNSNSNSNS(1, "Magic Idea", 1, "Wizard", 5, "Bone", 3, "Iron", 1, "Gold")

        // Add here what is given
        givenIdeaService.givenIdea("Magic Idea");
        givenStackService.givenStacks(1, Names.byNames(n1,s1).and(n2,s2).and(n3,s3).and(n4,s4).and(n5,s5).and(2,"Berry"));
        // And make sure that the game is in the right state (also for the frontend)
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);

    }

    public void theSMayCreateASCard(String s1, String s2) {
        // text:  * The "Magic Idea" may create a "Golem Spell" card.
        // code: this.theSMayCreateASCard("Magic Idea", "Golem Spell")
        // hint: Post_20221105_Coal_Context.theSMayCreateASCard

        var idea = getIdea(gameDTO, byName(s1));
        var rewards = idea.getCardRewards();
        assertThat(rewards).contains(s2);

    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20221122_Arrow_Context.endTheCurrentMoon

        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);

    }

    public void thereShouldBeNStacksOfNSNSAndNSCards(int expected, int n2, String s1, int n3, String s2, int n4, String s3) {
        // text:  * There should be 1 stacks of 1 "Magic Idea", 1 "Wizard" and 1 "Golem Spell" cards.
        // code: this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Magic Idea", 1, "Wizard", 1, "Golem Spell")
        // hint: Post_20221122_Arrow_Context.thereShouldBeNStacksOfNSNSAndNSCards
        var stacks = StackListDTO.findAllStack(gameDTO,
            byNames(n2, s1).and(n3,s2).and(n4,s3)
        );
        assertThat(stacks).hasSize(expected);


   }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
