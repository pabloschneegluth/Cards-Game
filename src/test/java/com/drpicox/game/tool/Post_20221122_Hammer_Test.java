package com.drpicox.game.tool;

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
public class Post_20221122_Hammer_Test {

    @Autowired Post_20221122_Hammer_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-22_hammer", "7b0588a8cff6ea45cd3b6f8fbb218f3b");
        context.beforeTest();

        // # Hammer                                                                                               // # Hammer

        // ## How to create a hammer                                                                              // ## How to create a hammer
        context.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Build Idea", 1, "Villager", 2, "Iron", 2, "Stone"); // * Given a new game with a stack of 1 "Build Idea", 1 "Villager" , 2 "Iron" and 2 "Stone" cards.
        context.endTheCurrentMoon();                                                                              // * End the current moon.
        context.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Hammer");             // * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Hammer" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
