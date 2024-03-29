import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221120_Egg_Context } from "./Post_20221120_Egg_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-20_egg.md", async () => {
  await runBeforeTestStarts(
    "food/2022-11-20_egg",
    "0ca6b31378734fdb924b6d7fed04ec24"
  );

  const context = new Post_20221120_Egg_Context();
  await context.beforeTest();

  // # Egg                                                    // # Egg
  // ### Eating an Egg                                        // ### Eating an Egg
  await context.givenThereAreNSCards(1, "Egg"); //            // * Given there are 1 "Egg" cards.
  await context.givenThereAreNSCards(1, "Villager"); //       // * Given there are 1 "Villager" cards.
  await context.theSumOfAllSTagsValueShouldBeN("Eats", 1); // // * The sum of all "Eats" tags value should be 1.
  await context.theSumOfAllSTagsValueShouldBeN("Food", 1); // // * The sum of all "Food" tags value should be 1.
  await context.endTheCurrentMoon(); //                       // * End the current moon.
  await context.thereShouldBeNSCards(1, "Villager"); //       // * There should be 1 "Villager" cards.
  await context.thereShouldBeNSCards(0, "Egg"); //            // * There should be 0 "Egg" cards.
  await context.theSumOfAllSTagsValueShouldBeN("Eats", 1); // // * The sum of all "Eats" tags value should be 1.
  await context.theSumOfAllSTagsValueShouldBeN("Food", 0); // // * The sum of all "Food" tags value should be 0.

  await context.afterTest();
  await runWhenTestSuccessful();
});
