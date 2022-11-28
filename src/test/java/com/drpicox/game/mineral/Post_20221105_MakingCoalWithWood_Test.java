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
public class Post_20221105_MakingCoalWithWood_Test {

    @Autowired Post_20221105_MakingCoalWithWood_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-05_making_coal_with_wood", "876470fbc06e5c625c0f3f7c5f6b13db");
        context.beforeTest();

        // # making coal with wood                                                                // # making coal with wood

        // ## HOW TO GET COAL BY BURNING WOOD                                                     // ## HOW TO GET COAL BY BURNING WOOD
        context.givenANewGameWithNStackOfNSNSAndNSCards(1, 1, "Villager", 1, "Flint", 1, "Wood"); // * Given a new game with 1 stack of 1 "Villager", 1 "Flint" and 1 "Wood" cards
        context.endTheCurrentMoon();                                                              // * End the current moon.
        context.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Villager", 1, "Flint", 2, "Coal");    // * There should be 1 stacks of 1 "Villager", 1 "Flint" and 2 "Coal" cards

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}