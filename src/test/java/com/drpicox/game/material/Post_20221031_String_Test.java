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
public class Post_20221031_String_Test {

    @Autowired Post_20221031_String_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-10-31_string", "00717932abd097a33314b80906a08abd");
        context.beforeTest();

        // # String                                                                                          // # String
        // ### Finding more things in the woods                                                              // ### Finding more things in the woods
        context.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager");           // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
        context.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 2, 0);                               // * Given there is the "Woods Stroll Idea" idea at level 2 and 0 XP.
        context.theSMayCreateASCard("Woods Stroll Idea", "String");                                          // * The "Woods Stroll Idea" may create a "String" card.
        context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("String", "Woods Stroll Idea");           // * Given that the odds are that we will get a "String" card from the "Woods Stroll Idea" card.
        context.endTheCurrentMoon();                                                                         // * End the current moon.
        context.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "String"); // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "String" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}