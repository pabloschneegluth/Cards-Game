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
public class Post_20221124_FindingThingsInSawmill_Test {

    @Autowired Post_20221124_FindingThingsInSawmill_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-24_finding_things_in_sawmill", "364a27190016ef349270f009fa6ab463");
        context.beforeTest();

        // # Finding things in Sawmill                                                                                       // # Finding things in Sawmill

        // ## | $CardName |                                                                                                  // ## | $CardName |

        // ## |-----------|                                                                                                  // ## |-----------|

        // ## | Wood      |                                                                                                  // ## | Wood      |
        context.givenANewGameWithAStackOfNSNSAndNSCards(1, "Sawmill Stroll Idea", 1, "Sawmill", 1, "Villager");              // * Given a new game with a stack of 1 "Sawmill Stroll Idea", 1 "Sawmill" and 1 "Villager" cards.
        context.theSMayCreateASCard("Sawmill Stroll Idea", "Wood");                                                          // * The "Sawmill Stroll Idea" may create a "Wood" card.
        context.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Wood", "Sawmill Stroll Idea");                               // * Given that the odds are that we will get a "Wood" from the "Sawmill Stroll Idea" card.
        context.endTheCurrentMoon();                                                                                         // * End the current moon.
        context.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Sawmill Stroll Idea", 1, "Sawmill", 1, "Villager", 2, "Wood"); // * There should be 1 stacks of 1 "Sawmill Stroll Idea", 1 "Sawmill", 1 "Villager" and 2 "Wood" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}