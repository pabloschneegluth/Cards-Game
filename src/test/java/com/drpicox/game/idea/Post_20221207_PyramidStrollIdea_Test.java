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
public class Post_20221207_PyramidStrollIdea_Test {

    @Autowired Post_20221207_PyramidStrollIdea_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-12-07_pyramid_stroll_idea", "3a57be31fea79fbac124f76d56d836a2");
        context.beforeTest();

        // # Pyramid Stroll idea                                                        // # Pyramid Stroll idea

        // ## How to obtain Pyramid Stroll Idea?                                        // ## How to obtain Pyramid Stroll Idea?
        context.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Pyramid", 1, "Villager"); // * Given a new game with a stack of 1 "Pyramid" cards and 1 "Villager" card.
        context.endTheCurrentMoon();                                                    // * End the current moon.
        context.thereShouldBeTheSIdea("Pyramid Stroll Idea");                           // * There should be the "Pyramid Stroll Idea" idea.
        context.theSShouldHaveLevelNAndNXp("Pyramid Stroll Idea", 1, 0);                // * The "Pyramid Stroll Idea" should have level 1 and 0 XP.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
