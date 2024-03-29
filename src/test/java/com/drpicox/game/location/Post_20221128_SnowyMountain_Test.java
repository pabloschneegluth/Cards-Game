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
public class Post_20221128_SnowyMountain_Test {

    @Autowired Post_20221128_SnowyMountain_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-28_snowy_mountain", "b04846a6b0896b77f832fc1afba265e4");
        context.beforeTest();

        // # Snowy Mountain                                                                                          // # Snowy Mountain

        // ## How to find an Snowy Mountain?                                                                         // ## How to find an Snowy Mountain?
        context.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager");                   // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
        context.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 3, 0);                                       // * Given there is the "Woods Stroll Idea" idea at level 3 and 0 XP.
        context.theSMayCreateASCard("Woods Stroll Idea", "Snowy Mountain");                                          // * The "Woods Stroll Idea" may create a "Snowy Mountain" card.
        context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Snowy Mountain", "Woods Stroll Idea");           // * Given that the odds are that we will get a "Snowy Mountain" card from the "Woods Stroll Idea" card.
        context.endTheCurrentMoon();                                                                                 // * End the current moon.
        context.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Snowy Mountain"); // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Snowy Mountain" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
