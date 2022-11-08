package com.drpicox.game.tag.food;

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
public class Post_20220721_MoreDetailsAboutHowVillagersEatFood_Test {

    @Autowired Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-07-21_more_details_about_how_villagers_eat_food", "a715c9d38c1b983384c51c11fcb962ab");
        context.beforeTest();

        // # More Details About How Villagers Eat Food                                         // # More Details About How Villagers Eat Food

        // ## More kinds of villagers                                                          // ## More kinds of villagers
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(1, "Villager", 1, "Militia");                        // * Given there are 1 "Villager" and 1 "Militia" cards.
        context.theSCardShouldHaveNInSTag("Villager", 1, "Eats");                              // * The "Villager" card should have 1 in "Eats" tag.
        context.theSCardShouldHaveNInSTag("Militia", 2, "Eats");                               // * The "Militia" card should have 2 in "Eats" tag.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);                                     // * The sum of all "Eats" tags value should be 3.

        // ## More kinds of food                                                               // ## More kinds of food
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(1, "Berry", 1, "Apple");                             // * Given there are 1 "Berry" and 1 "Apple" cards.
        context.theSCardShouldHaveNInSTag("Berry", 1, "Food");                                 // * The "Berry" card should have 1 in "Food" tag.
        context.theSCardShouldHaveNInSTag("Apple", 2, "Food");                                 // * The "Apple" card should have 2 in "Food" tag.
        context.theSumOfAllSTagsValueShouldBeN("Food", 3);                                     // * The sum of all "Food" tags value should be 3.

        // ## Eating                                                                           // ## Eating
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(1, "Villager", 1, "Militia");                        // * Given there are 1 "Villager" and 1 "Militia" cards.
        context.givenThereAreNSAndNSCards(1, "Berry", 1, "Apple");                             // * Given there are 1 "Berry" and 1 "Apple" cards.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);                                     // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 0);                                     // * The sum of all "Food" tags value should be 0.

        // ## Too much food                                                                    // ## Too much food
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(1, "Villager", 1, "Militia");                        // * Given there are 1 "Villager" and 1 "Militia" cards.
        context.givenThereAreNSAndNSCards(4, "Berry", 0, "Apple");                             // * Given there are 4 "Berry" and 0 "Apple" cards.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);                                     // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 4);                                     // * The sum of all "Food" tags value should be 4.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);                                     // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 1);                                     // * The sum of all "Food" tags value should be 1.
        context.thereShouldBeNSCards(1, "Berry");                                              // * There should be 1 "Berry" cards.
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(1, "Villager", 1, "Militia");                        // * Given there are 1 "Villager" and 1 "Militia" cards.
        context.givenThereAreNSAndNSCards(0, "Berry", 2, "Apple");                             // * Given there are 0 "Berry" and 2 "Apple" cards.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);                                     // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 4);                                     // * The sum of all "Food" tags value should be 4.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);                                     // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 0);                                     // * The sum of all "Food" tags value should be 0.
        context.thereShouldBeNSCards(0, "Apple");                                              // * There should be 0 "Apple" cards.

        // ## Too few food                                                                     // ## Too few food
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(0, "Villager", 2, "Militia");                        // * Given there are 0 "Villager" and 2 "Militia" cards.
        context.givenThereAreNSAndNSCards(3, "Berry", 0, "Apple");                             // * Given there are 3 "Berry" and 0 "Apple" cards.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 4);                                     // * The sum of all "Eats" tags value should be 4.
        context.theSumOfAllSTagsValueShouldBeN("Food", 3);                                     // * The sum of all "Food" tags value should be 3.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 2);                                     // * The sum of all "Eats" tags value should be 2.
        context.theSumOfAllSTagsValueShouldBeN("Food", 1);                                     // * The sum of all "Food" tags value should be 1.
        context.thereShouldBeNSCards(1, "Militia");                                            // * There should be 1 "Militia" cards.
        context.thereShouldBeNSCards(1, "Berry");                                              // * There should be 1 "Berry" cards.

        // ## Choosing what to eat                                                             // ## Choosing what to eat
        // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples | // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples |
        // ### |------------|----------|----------|---------|----------------|---------------| // ### |------------|----------|----------|---------|----------------|---------------|
        // ### | 1          | 0        | 1        | 0       | 0              | 0             | // ### | 1          | 0        | 1        | 0       | 0              | 0             |
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(1, "Villager", 0, "Militia");                        // * Given there are 1 "Villager" and 0 "Militia" cards.
        context.givenThereAreNSAndNSCards(1, "Berry", 0, "Apple");                             // * Given there are 1 "Berry" and 0 "Apple" cards.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.thereShouldBeNSCards(0, "Berry");                                              // * There should be 0 "Berry" cards.
        context.thereShouldBeNSCards(0, "Apple");                                              // * There should be 0 "Apple" cards.

        // ## Choosing what to eat                                                             // ## Choosing what to eat
        // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples | // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples |
        // ### |------------|----------|----------|---------|----------------|---------------| // ### |------------|----------|----------|---------|----------------|---------------|
        // ### | 1          | 0        | 0        | 1       | 0              | 0             | // ### | 1          | 0        | 0        | 1       | 0              | 0             |
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(1, "Villager", 0, "Militia");                        // * Given there are 1 "Villager" and 0 "Militia" cards.
        context.givenThereAreNSAndNSCards(0, "Berry", 1, "Apple");                             // * Given there are 0 "Berry" and 1 "Apple" cards.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.thereShouldBeNSCards(0, "Berry");                                              // * There should be 0 "Berry" cards.
        context.thereShouldBeNSCards(0, "Apple");                                              // * There should be 0 "Apple" cards.

        // ## Choosing what to eat                                                             // ## Choosing what to eat
        // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples | // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples |
        // ### |------------|----------|----------|---------|----------------|---------------| // ### |------------|----------|----------|---------|----------------|---------------|
        // ### | 1          | 0        | 1        | 1       | 0              | 1             | // ### | 1          | 0        | 1        | 1       | 0              | 1             |
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(1, "Villager", 0, "Militia");                        // * Given there are 1 "Villager" and 0 "Militia" cards.
        context.givenThereAreNSAndNSCards(1, "Berry", 1, "Apple");                             // * Given there are 1 "Berry" and 1 "Apple" cards.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.thereShouldBeNSCards(0, "Berry");                                              // * There should be 0 "Berry" cards.
        context.thereShouldBeNSCards(1, "Apple");                                              // * There should be 1 "Apple" cards.

        // ## Choosing what to eat                                                             // ## Choosing what to eat
        // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples | // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples |
        // ### |------------|----------|----------|---------|----------------|---------------| // ### |------------|----------|----------|---------|----------------|---------------|
        // ### | 0          | 1        | 2        | 0       | 0              | 0             | // ### | 0          | 1        | 2        | 0       | 0              | 0             |
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(0, "Villager", 1, "Militia");                        // * Given there are 0 "Villager" and 1 "Militia" cards.
        context.givenThereAreNSAndNSCards(2, "Berry", 0, "Apple");                             // * Given there are 2 "Berry" and 0 "Apple" cards.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.thereShouldBeNSCards(0, "Berry");                                              // * There should be 0 "Berry" cards.
        context.thereShouldBeNSCards(0, "Apple");                                              // * There should be 0 "Apple" cards.

        // ## Choosing what to eat                                                             // ## Choosing what to eat
        // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples | // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples |
        // ### |------------|----------|----------|---------|----------------|---------------| // ### |------------|----------|----------|---------|----------------|---------------|
        // ### | 0          | 1        | 0        | 1       | 0              | 0             | // ### | 0          | 1        | 0        | 1       | 0              | 0             |
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(0, "Villager", 1, "Militia");                        // * Given there are 0 "Villager" and 1 "Militia" cards.
        context.givenThereAreNSAndNSCards(0, "Berry", 1, "Apple");                             // * Given there are 0 "Berry" and 1 "Apple" cards.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.thereShouldBeNSCards(0, "Berry");                                              // * There should be 0 "Berry" cards.
        context.thereShouldBeNSCards(0, "Apple");                                              // * There should be 0 "Apple" cards.

        // ## Choosing what to eat                                                             // ## Choosing what to eat
        // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples | // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples |
        // ### |------------|----------|----------|---------|----------------|---------------| // ### |------------|----------|----------|---------|----------------|---------------|
        // ### | 0          | 1        | 2        | 1       | 2              | 0             | // ### | 0          | 1        | 2        | 1       | 2              | 0             |
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(0, "Villager", 1, "Militia");                        // * Given there are 0 "Villager" and 1 "Militia" cards.
        context.givenThereAreNSAndNSCards(2, "Berry", 1, "Apple");                             // * Given there are 2 "Berry" and 1 "Apple" cards.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.thereShouldBeNSCards(2, "Berry");                                              // * There should be 2 "Berry" cards.
        context.thereShouldBeNSCards(0, "Apple");                                              // * There should be 0 "Apple" cards.

        // ## Choosing what to eat                                                             // ## Choosing what to eat
        // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples | // ### | $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples |
        // ### |------------|----------|----------|---------|----------------|---------------| // ### |------------|----------|----------|---------|----------------|---------------|
        // ### | 1          | 1        | 2        | 2       | 1              | 1             | // ### | 1          | 1        | 2        | 2       | 1              | 1             |
        context.givenANewExample();                                                            // * Given a new example.
        context.givenThereAreNSAndNSCards(1, "Villager", 1, "Militia");                        // * Given there are 1 "Villager" and 1 "Militia" cards.
        context.givenThereAreNSAndNSCards(2, "Berry", 2, "Apple");                             // * Given there are 2 "Berry" and 2 "Apple" cards.
        context.endTheCurrentMoon();                                                           // * End the current moon.
        context.thereShouldBeNSCards(1, "Berry");                                              // * There should be 1 "Berry" cards.
        context.thereShouldBeNSCards(1, "Apple");                                              // * There should be 1 "Apple" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
