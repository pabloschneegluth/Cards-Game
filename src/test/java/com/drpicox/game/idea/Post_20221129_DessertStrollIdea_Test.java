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
public class Post_20221129_DessertStrollIdea_Test {

    @Autowired Post_20221129_DessertStrollIdea_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-29_dessert_stroll_idea", "5317b42907f7a5c202c5afab404b22e9");
        context.beforeTest();

        // # Dessert Stroll idea                                                        // # Dessert Stroll idea

        // ## How to obtain Dessert Stroll Idea?                                        // ## How to obtain Dessert Stroll Idea?
        context.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Dessert", 1, "Villager"); // * Given a new game with a stack of 1 "Dessert" cards and 1 "Villager" card.
        context.endTheCurrentMoon();                                                    // * End the current moon.
        context.thereShouldBeTheSIdea("Dessert Stroll Idea");                           // * There should be the "Dessert Stroll Idea" idea.
        context.theSShouldHaveLevelNAndNXp("Dessert Stroll Idea", 1, 0);                // * The "Dessert Stroll Idea" should have level 1 and 0 XP.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}