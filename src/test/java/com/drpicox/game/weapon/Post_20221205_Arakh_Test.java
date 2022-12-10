package com.drpicox.game.weapon;

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
public class Post_20221205_Arakh_Test {

    @Autowired Post_20221205_Arakh_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-12-05_arakh", "47e1d1ce130a4f2da3eb1b754af37f80");
        context.beforeTest();

        // # Arakh                                                                                                                            // # Arakh

        // ## How to create the Arakh                                                                                                         // ## How to create the Arakh
        context.givenANewGameWithAStackOfNSNSNSNSNSAndNSCards(1, "Build Idea", 1, "Villager", 2, "Wood", 1, "Iron", 1, "Steel", 2, "String"); // * Given a new game with a stack of 1 "Build Idea", 1 "Villager", 2 "Wood", 1 "Iron", 1 "Steel" and 2 "String" cards.
        context.endTheCurrentMoon();                                                                                                          // * End the current moon.
        context.thereShouldBeNStacksOfNSNSNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Arakh");                                             // * There should be 1 stacks of 1 "Build Idea", 1 "Villager", 1 "Arakh" cards

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
