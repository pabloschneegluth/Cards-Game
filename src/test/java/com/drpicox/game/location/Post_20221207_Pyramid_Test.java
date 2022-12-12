package com.drpicox.game.location;

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
public class Post_20221207_Pyramid_Test {

    @Autowired Post_20221207_Pyramid_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-12-07_pyramid", "86456700fb49e4fcc8665ce7d62d96d2");
        context.beforeTest();

        // # Pyramid                                                                                                            // # Pyramid

        // ## How to find a Pyramid?                                                                                            // ## How to find a Pyramid?
        context.givenANewGameWithAStackOfNSNSAndNSCards(1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager");                 // * Given a new game with a stack of 1 "Dessert Stroll Idea", 1 "Dessert" and 1 "Villager" cards.
        context.givenThereIsTheSIdeaAtLevelNAndNXp("Dessert Stroll Idea", 4, 0);                                                // * Given there is the "Dessert Stroll Idea" idea at level 4 and 0 XP.
        context.theSMayCreateASCard("Dessert Stroll Idea", "Pyramid");                                                          // * The "Dessert Stroll Idea" may create a "Pyramid" card.
        context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Pyramid", "Dessert Stroll Idea");                           // * Given that the odds are that we will get a "Pyramid" card from the "Dessert Stroll Idea" card.
        context.endTheCurrentMoon();                                                                                            // * End the current moon.
        context.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager", 1, "Pyramid"); // * There should be 1 stacks of 1 "Dessert Stroll Idea", 1 "Dessert", 1 "Villager" and 1 "Pyramid" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
