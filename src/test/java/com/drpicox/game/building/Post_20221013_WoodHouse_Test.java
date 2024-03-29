package com.drpicox.game.building;

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
public class Post_20221013_WoodHouse_Test {

    @Autowired Post_20221013_WoodHouse_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-10-13_wood_house", "72f502681469f905ca2388c948d55905");
        context.beforeTest();

        // # Wood House                                                                                             // # Wood House

        // ## How to build a Wood House                                                                             // ## How to build a Wood House
        context.givenANewGameWithAStackOfNSCardsNSCardsAndNSCards(1, "Build Idea", 1, "Villager", 4, "Wood");       // * Given a new game with a stack of 1 "Build Idea" cards, 1 "Villager" cards and 4 "Wood" cards
        context.endTheCurrentMoon();                                                                                // * End the current moon.
        context.thereShouldBeNStacksOfNSCardsNSCardsAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Wood House"); // * There should be 1 stacks of 1 "Build Idea" cards, 1 "Villager" cards and 1 "Wood House" cards

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
