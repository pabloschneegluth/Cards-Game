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
public class Post_20221115_LakeStrollIdea_Test {

    @Autowired Post_20221115_LakeStrollIdea_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-15_lake_stroll_idea", "41d1f8790147c0abd4aa5758358743aa");
        context.beforeTest();

        // # Lake Stroll idea                                                        // # Lake Stroll idea

        // ## How to get Lake Stroll Idea?                                           // ## How to get Lake Stroll Idea?
        context.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Lake", 1, "Villager"); // * Given a new game with a stack of 1 "Lake" cards and 1 "Villager" card.
        context.endTheCurrentMoon();                                                 // * End the current moon.
        context.thereShouldBeTheSIdea("Lake Stroll Idea");                           // * There should be the "Lake Stroll Idea" idea.
        context.theSShouldHaveLevelNAndNXp("Lake Stroll Idea", 1, 0);                // * The "Lake Stroll Idea" should have level 1 and 0 XP.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
