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
public class Post_20221019_Farm_Test {

    @Autowired Post_20221019_Farm_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-10-19_farm", "ce6fee9e765b7ccb95f3a8a5f56b27de");
        context.beforeTest();

        // # farm                                                                                                                // # farm
        context.thereShouldBeNSCard(0, "Farm");                                                                                  // * there should be 0 "Farm" card.
        context.givenThereIsNStackOfNSAtLevelNAndNXpNSNSNSCards(1, 1, "Build Idea", 2, 6, 1, "Villager", 1, "Stone", 2, "Wood"); // * given there is 1 stack of 1 "Build Idea" at level 2 and 6 XP, 1 "Villager", 1 "Stone", 2 "Wood" cards
        context.endTheCurrentMoon();                                                                                             // * end the current moon.
        context.thereShouldBeNStackOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Farm");                               // * there should be 1 stack of 1 "Build Idea", 1 "Villager" and 1 "Farm" cards

        // ## how the farm works                                                                                                 // ## how the farm works
        context.givenANewEmptyGame();                                                                                            // * Given a new empty game
        context.givenThereIsNStackOfNSCardsNSCardsAndNSCardsOrAnotherPlant(1, 1, "Farm", 1, "Villager", 1, "Berry Bush");        // * Given there is 1 stack of 1 "Farm" cards, 1 "Villager" cards and 1 "Berry Bush" cards or another plant.
        context.endTheCurrentMoon();                                                                                             // * end the current moon.
        context.thereShouldBeNStackOfNSCardsNSCardsNSAndNSCards(1, 1, "Farm", 1, "Villager", 1, "Berry Bush", 5, "Berry");       // * There should be 1 stack of 1 "Farm" cards, 1 "Villager" cards, 1 "Berry Bush" and 5 "Berry" cards

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
