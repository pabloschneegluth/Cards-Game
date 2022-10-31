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
public class Post_20221030_Cow_Test {

    @Autowired Post_20221030_Cow_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-10-30_cow", "34697877c1256e34ef9efd578ccbfd6a");
        context.beforeTest();

        // # Cow!                                                                                         // # Cow!
        // # How to find a cow                                                                            // # How to find a cow
        context.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager");        // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
        context.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 4, 20);                           // * Given there is the "Woods Stroll Idea" idea at level 4 and 20 XP.
        context.theSMayCreateASCard("Woods Stroll Idea", "Cow");                                          // * The "Woods Stroll Idea" may create a "Cow" card.
        context.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Cow", "Woods Stroll Idea");               // * Given that the odds are that we will get a "Cow" from the "Woods Stroll Idea" card.
        context.endTheCurrentMoon();                                                                      // * End the current moon.
        context.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Cow"); // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Cow" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
