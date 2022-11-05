package com.drpicox.game.artifact;

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
public class Post_20221103_Key_Test {

    @Autowired Post_20221103_Key_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-03_key", "0ee83df7621554e1c78472004714874f");
        context.beforeTest();

        // # Key                                                                                          // # Key

        // ## How to find a Key?                                                                          // ## How to find a Key?
        context.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager");        // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
        context.theSMayCreateASCard("Woods Stroll Idea", "Key");                                          // * The "Woods Stroll Idea" may create a "Key" card.
        context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Key", "Woods Stroll Idea");           // * Given that the odds are that we will get a "Key" card from the "Woods Stroll Idea" card.
        context.endTheCurrentMoon();                                                                      // * End the current moon.
        context.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Key"); // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Key" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
