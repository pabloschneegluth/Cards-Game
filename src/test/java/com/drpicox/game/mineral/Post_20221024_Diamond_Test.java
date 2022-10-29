package com.drpicox.game.mineral;

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
public class Post_20221024_Diamond_Test {

    @Autowired Post_20221024_Diamond_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-10-24_diamond", "47ed75686e1dc11294e1ccfbf421d090");
        context.beforeTest();

        // # Diamond                                                                                          // # Diamond

        // ## How to obtain diamond?                                                                          // ## How to obtain diamond?
        context.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager");            // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
        context.theSMayCreateASCard("Woods Stroll Idea", "Diamond");                                          // * The "Woods Stroll Idea" may create a "Diamond" card.
        context.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Diamond", "Woods Stroll Idea");               // * Given that the odds are that we will get a "Diamond" from the "Woods Stroll Idea" card.
        context.endTheCurrentMoon();                                                                          // * End the current moon.
        context.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Diamond"); // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Diamond" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
