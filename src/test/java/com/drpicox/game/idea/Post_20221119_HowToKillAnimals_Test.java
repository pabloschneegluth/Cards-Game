package com.drpicox.game.idea;

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
public class Post_20221119_HowToKillAnimals_Test {

    @Autowired Post_20221119_HowToKillAnimals_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-19_how_to_kill_animals", "f73ab7a7438b6619dc5c8472207674ff");
        context.beforeTest();

        // # How To Kill Animals                                               // # How To Kill Animals

        // ## How animals can be killed?                                       // ## How animals can be killed?
        context.givenANewGameWithAStackOfNSAndNSCards(1, "Militia", 1, "Cow"); // * Given a new game with a stack of 1 "Militia" and 1 "Cow" cards.
        context.endTheCurrentMoon();                                           // * End the current moon.
        context.thereShouldBeAStackWithNSAndNSCards(1, "Militia", 3, "Meat");  // * There should be a stack with 1 "Militia" and 3 "Meat" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
