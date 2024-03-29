package com.drpicox.game.mineral;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.drpicox.game.util.TestUtils;

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class Post_20221105_Coal_Test {

    @Autowired Post_20221105_Coal_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-05_coal", "3bada2faa764f46ad1469ba7a50707f9");
        context.beforeTest();

        // # COAL                                                                                                                      // # COAL

        // ## HOW TO ACQUIRE COAL IN THE MINE                                                                                          // ## HOW TO ACQUIRE COAL IN THE MINE
        context.givenANewGameWithNStackOfNSNSNSAndNSCards(1, 1, "Mine Stroll Idea", 1, "Mine", 1, "Villager", 1, "Pickaxe");           // * Given a new game with 1 stack of 1 "Mine Stroll Idea", 1 "Mine", 1 "Villager" and 1 "Pickaxe" cards
        context.theSMayCreateASCard("Mine Stroll Idea", "Stone");                                                                      // * The "Mine Stroll Idea" may create a "Stone" card.
        context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Coal", "Mine Stroll Idea");                                        // * Given that the odds are that we will get a "Coal" card from the "Mine Stroll Idea" card.
        context.endTheCurrentMoon();                                                                                                   // * End the current moon.
        context.thereShouldBeNStacksOfNSNSNSNSAndNSCards(1, 1, "Mine Stroll Idea", 1, "Mine", 1, "Villager", 1, "Pickaxe", 1, "Coal"); // * There should be 1 stacks of 1 "Mine Stroll Idea", 1 "Mine", 1 "Villager", 1 "Pickaxe" and 1 "Coal" cards

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
