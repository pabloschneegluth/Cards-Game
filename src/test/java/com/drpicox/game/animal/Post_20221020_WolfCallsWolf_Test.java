package com.drpicox.game.animal;

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
public class Post_20221020_WolfCallsWolf_Test {

    @Autowired Post_20221020_WolfCallsWolf_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-10-20_wolf_calls_wolf", "312f5c322bb5c98b5c4dd095ee933f4f");
        context.beforeTest();

        // # Wolf Calls wolf                     // # Wolf Calls wolf
        context.givenThereAreNSCards(1, "Wolf"); // * Given there are 1 "Wolf" cards.
        context.endTheCurrentMoon();             // * End the current moon.
        context.thereShouldBeNSCards(2, "Wolf"); // * There should be 2 "Wolf" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}