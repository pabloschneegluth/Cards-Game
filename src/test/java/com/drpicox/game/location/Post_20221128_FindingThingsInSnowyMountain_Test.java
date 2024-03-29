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
public class Post_20221128_FindingThingsInSnowyMountain_Test {

    @Autowired Post_20221128_FindingThingsInSnowyMountain_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-28_finding_things_in_snowy_mountain", "8413cae96b732961b55911c43144dccf");
        context.beforeTest();

        // # Finding things in Snowy Mountain                                                                                                     // # Finding things in Snowy Mountain

        // ## | $CardName        |                                                                                                                // ## | $CardName        |

        // ## |------------------|                                                                                                                // ## |------------------|

        // ## | Grizzly          |                                                                                                                // ## | Grizzly          |
        context.givenANewGameWithAStackOfNSNSNSCardAndNS(1, "Snowy Mountain Stroll Idea", 1, "Snowy Mountain", 1, "Villager", 1, "Mountain Kit"); // * Given a new game with a stack of 1 "Snowy Mountain Stroll Idea", 1 "Snowy Mountain", 1 "Villager" card and 1 "Mountain Kit".
        context.theSMayCreateASCard("Snowy Mountain Stroll Idea", "Grizzly");                                                                     // * The "Snowy Mountain Stroll Idea" may create a "Grizzly" card.
        context.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Grizzly", "Snowy Mountain");                                                      // * Given that the odds are that we will get a "Grizzly" from the "Snowy Mountain" card.
        context.endTheCurrentMoon();                                                                                                              // * End the current moon.
        context.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Snowy Mountain Stroll Idea", 1, "Snowy Mountain", 1, "Villager", 1, "Grizzly");     // * There should be 1 stacks of 1 "Snowy Mountain Stroll Idea", 1 "Snowy Mountain", 1 "Villager" and 1 "Grizzly" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
