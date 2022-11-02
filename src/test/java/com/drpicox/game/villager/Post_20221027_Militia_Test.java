package com.drpicox.game.villager;

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
public class Post_20221027_Militia_Test {

    @Autowired Post_20221027_Militia_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-10-27_militia", "1e82756bd3fa29f980895f18155e4fb9");
        context.beforeTest();

        // # Militia                                                           // # Militia

        // ## How to transform a militia                                       // ## How to transform a militia
        context.givenANewGameWithAStackOfNSNSCards(1, "Villager", 1, "Sword"); // * Given a new game with a stack of 1 "Villager", 1 "Sword" cards.
        context.endTheCurrentMoon();                                           // * End the current moon.
        context.thereShouldBeNStacksOfNSCards(1, 1, "Militia");                // * There should be 1 stacks of 1 "Militia" cards

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
