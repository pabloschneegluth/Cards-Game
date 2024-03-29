package com.drpicox.game.location;

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
public class Post_20221129_FindingThingsInDessert_Test {

    @Autowired Post_20221129_FindingThingsInDessert_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-29_finding_things_in_dessert", "70bd9569ab49ab2c8a5a8c86c4a6b881");
        context.beforeTest();

        // # Finding things in Dessert                                                                                           // # Finding things in Dessert

        // ## | $CardName   |                                                                                                    // ## | $CardName   |

        // ## |-------------|                                                                                                    // ## |-------------|

        // ## | Scorpion    |                                                                                                    // ## | Scorpion    |
        context.givenANewGameWithAStackOfNSNSAndNS(1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager");                       // * Given a new game with a stack of 1 "Dessert Stroll Idea", 1 "Dessert" and 1 "Villager".
        context.theSMayCreateASCard("Dessert Stroll Idea", "Scorpion");                                                          // * The "Dessert Stroll Idea" may create a "Scorpion" card.
        context.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Scorpion", "Dessert Stroll Idea");                               // * Given that the odds are that we will get a "Scorpion" from the "Dessert Stroll Idea" card.
        context.endTheCurrentMoon();                                                                                             // * End the current moon.
        context.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager", 1, "Scorpion"); // * There should be 1 stacks of 1 "Dessert Stroll Idea", 1 "Dessert", 1 "Villager" and 1 "Scorpion" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
