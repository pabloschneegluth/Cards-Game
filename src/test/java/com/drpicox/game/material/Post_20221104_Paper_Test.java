package com.drpicox.game.material;

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
public class Post_20221104_Paper_Test {

    @Autowired Post_20221104_Paper_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-04_paper", "048eb415bfc62f1764437522a13159ae");
        context.beforeTest();

        // # Paper                                                                                       // # Paper

        // ## How to obtain paper                                                                        // ## How to obtain paper
        context.givenANewGameWithAStackOfNSCardsNSAndNSCards(5, "Wood", 1, "Build Idea", 1, "Villager"); // * Given a new game with a stack of 5 "Wood" cards, 1 "Build Idea" and 1 "Villager" cards.
        context.givenThereIsTheSIdeaAtLevelNAndNXp("Build Idea", 2, 0);                                  // * Given there is the "Build Idea" idea at level 2 and 0 XP.
        context.endTheCurrentMoon();                                                                     // * End the current moon.
        context.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Paper");     // * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Paper" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
