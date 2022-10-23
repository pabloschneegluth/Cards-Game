import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221020_Pear_Context } from "./Post_20221020_Pear_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-10-20_pear.md", async () => {
  await runBeforeTestStarts(
    "2022-10-20_pear",
    "e80690581d355a4cc75706d45f11eb3d"
  );

  const context = new Post_20221020_Pear_Context();
  await context.beforeTest();

  // # Pear                                                   // # Pear
  // ### Eating a Pear                                        // ### Eating a Pear
  await context.givenThereAreNSCards(1, "Pear"); //           // * Given there are 1 "Pear" cards.
  await context.givenThereAreNSCards(3, "Villager"); //       // * Given there are 3 "Villager" cards.
  await context.theSumOfAllSTagsValueShouldBeN("Eats", 3); // // * The sum of all "Eats" tags value should be 3.
  await context.theSumOfAllSTagsValueShouldBeN("Food", 3); // // * The sum of all "Food" tags value should be 3.
  await context.endTheCurrentMoon(); //                       // * End the current moon.
  await context.thereShouldBeNSCards(3, "Villager"); //       // * There should be 3 "Villager" cards.
  await context.thereShouldBeNSCards(0, "Pear"); //           // * There should be 0 "Pear" cards.
  await context.theSumOfAllSTagsValueShouldBeN("Eats", 3); // // * The sum of all "Eats" tags value should be 3.
  await context.theSumOfAllSTagsValueShouldBeN("Food", 0); // // * The sum of all "Food" tags value should be 0.

  await context.afterTest();
  await runWhenTestSuccessful();
});
