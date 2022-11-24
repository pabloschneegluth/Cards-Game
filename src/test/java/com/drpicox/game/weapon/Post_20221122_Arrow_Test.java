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
public class Post_20221122_Arrow_Test {

    @Autowired Post_20221122_Arrow_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-22_arrow", "6e5aa29549c993722951d32897a8b5af");
        context.beforeTest();

        // # Arrow                                                                                                          // # Arrow

        // ## How to create an Arrow?                                                                                       // ## How to create an Arrow?
        context.givenANewGameWithAStackOfNSNSCardsNSCardsAndNSCards(1, "Build Idea", 1, "Villager", 1, "Stone", 1, "Wood"); // * Given a new game with a stack of 1 "Build Idea", 1 "Villager" cards, 1 "Stone" cards and 1 "Wood" cards.
        context.givenThereIsTheSIdeaAtLevelNAndNXp("Build Idea", 1, 0);                                                     // * Given there is the "Build Idea" idea at level 1 and 0 XP.
        context.endTheCurrentMoon();                                                                                        // * End the current moon.
        context.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Arrow");                        // * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Arrow" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}