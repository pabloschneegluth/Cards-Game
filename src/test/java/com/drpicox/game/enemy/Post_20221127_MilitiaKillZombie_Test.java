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
public class Post_20221127_MilitiaKillZombie_Test {

    @Autowired Post_20221127_MilitiaKillZombie_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-27_militia_kill_zombie", "8599a37d2a8b8144ec35a9afc5b6e8e2");
        context.beforeTest();

        // # Militia Kill Zombie                    // # Militia Kill Zombie

        // ## How to kill a zombie?                 // ## How to kill a zombie?
        context.givenThereAreNSCards(1, "Militia"); // * Given there are 1 "Militia" cards.
        context.givenThereAreNSCards(1, "Zombie");  // * Given there are 1 "Zombie" cards.
        context.endTheCurrentMoon();                // * End the current moon.
        context.thereShouldBeNSCards(0, "Zombie");  // * There should be 0 "Zombie" cards.
        context.thereShouldBeNSCards(1, "Militia"); // * There should be 1 "Militia" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
