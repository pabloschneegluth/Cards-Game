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
public class Post_20221024_Pickaxe_Test {

    @Autowired Post_20221024_Pickaxe_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-10-24_pickaxe", "d94c94d8247de08d3fb2c14a40b9691d");
        context.beforeTest();

        // # Pickaxe                                                                                             // # Pickaxe

        // ## How to create the pickaxe                                                                          // ## How to create the pickaxe
        context.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Build Idea", 1, "Villager", 2, "Wood", 3, "Iron"); // * Given a new game with a stack of 1 "Build Idea", 1 "Villager", 2 "Wood" and 3 "Iron" cards.
        context.endTheCurrentMoon();                                                                             // * End the current moon.
        context.thereShouldBeNStacksOfNSNSNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Pickaxe");              // * There should be 1 stacks of 1 "Build Idea", 1 "Villager", 1 "Pickaxe" cards

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
