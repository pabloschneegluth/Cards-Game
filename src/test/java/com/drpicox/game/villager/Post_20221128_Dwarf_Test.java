package com.drpicox.game.villager;

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
public class Post_20221128_Dwarf_Test {

    @Autowired Post_20221128_Dwarf_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-11-28_dwarf", "296bcdc5a0ca64c8678965150b60f69f");
        context.beforeTest();

        // # Dwarf                                                           // # Dwarf

        // ## How to transform a dwarf                                       // ## How to transform a dwarf
        context.givenANewGameWithAStackOfNSNSCards(1, "Villager", 1, "Axe"); // * Given a new game with a stack of 1 "Villager", 1 "Axe" cards.
        context.endTheCurrentMoon();                                         // * End the current moon.
        context.thereShouldBeNStacksOfNSCards(1, 1, "Dwarf");                // * There should be 1 stacks of 1 "Dwarf" cards

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
