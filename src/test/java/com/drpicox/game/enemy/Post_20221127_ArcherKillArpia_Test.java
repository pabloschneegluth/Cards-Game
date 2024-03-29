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
public class Post_20221127_ArcherKillArpia_Test {

    @Autowired Post_20221127_ArcherKillArpia_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-27_archer_kill_arpia", "fd400b6013cbb5edf4c83fa6fca8f94a");
        context.beforeTest();

        // # Archer Kill Arpia                     // # Archer Kill Arpia

        // ## How to kill a arpia?                 // ## How to kill a arpia?
        context.givenThereAreNSCards(1, "Archer"); // * Given there are 1 "Archer" cards.
        context.givenThereAreNSCards(1, "Arpia");  // * Given there are 1 "Arpia" cards.
        context.endTheCurrentMoon();               // * End the current moon.
        context.thereShouldBeNSCards(0, "Arpia");  // * There should be 0 "Arpia" cards.
        context.thereShouldBeNSCards(1, "Archer"); // * There should be 1 "Archer" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
