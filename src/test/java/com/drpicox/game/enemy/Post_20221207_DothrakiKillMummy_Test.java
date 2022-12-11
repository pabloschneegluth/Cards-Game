package com.drpicox.game.enemy;

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
public class Post_20221207_DothrakiKillMummy_Test {

    @Autowired Post_20221207_DothrakiKillMummy_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-12-07_dothraki_kill_mummy", "fd22739d32caacaeb895a5c763cc778e");
        context.beforeTest();

        // # Dothraki Kill Mummy                     // # Dothraki Kill Mummy

        // ## How to kill a Mummy?                   // ## How to kill a Mummy?
        context.givenThereAreNSCards(1, "Dothraki"); // * Given there are 1 "Dothraki" cards.
        context.givenThereAreNSCards(1, "Mummy");    // * Given there are 1 "Mummy" cards.
        context.endTheCurrentMoon();                 // * End the current moon.
        context.thereShouldBeNSCards(0, "Mummy");    // * There should be 0 "Mummy" cards.
        context.thereShouldBeNSCards(1, "Dothraki"); // * There should be 1 "Dothraki" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
