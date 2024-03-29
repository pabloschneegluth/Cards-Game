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
public class Post_20221114_WolfKillVillager_Test {

    @Autowired Post_20221114_WolfKillVillager_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-14_wolf_kill_villager", "eb8fc1c21e4256efb1b645abdf11e237");
        context.beforeTest();

        // # Wolf Kill Villager!                     // # Wolf Kill Villager!
        // # How wolf kills Villager?                // # How wolf kills Villager?
        context.givenThereAreNSCards(1, "Wolf");     // * Given there are 1 "Wolf" cards.
        context.givenThereAreNSCards(1, "Villager"); // * Given there are 1 "Villager" cards.
        context.endTheCurrentMoon();                 // * End the current moon.
        context.thereShouldBeNSCards(0, "Villager"); // * There should be 0 "Villager" cards.
        context.thereShouldBeNSCards(2, "Wolf");     // * There should be 2 "Wolf" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
