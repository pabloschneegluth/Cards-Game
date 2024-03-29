import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221119_Meat_Context } from "./Post_20221119_Meat_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-19_meat.md", async () => {
  await runBeforeTestStarts(
    "food/2022-11-19_meat",
    "d10fea36440d09ea93e414bfaee7f554"
  );

  const context = new Post_20221119_Meat_Context();
  await context.beforeTest();

  // # MEAT                                                                         // # MEAT
  await context.givenANewGameWithAStackOfNSAndNSCards(4, "Villager", 1, "Meat"); // // * Given a new game with a stack of 4 "Villager" and 1 "Meat" cards
  await context.theSumOfAllSTagsValueShouldBeN("Eats", 4); //                       // * The sum of all "Eats" tags value should be 4.
  await context.theSumOfAllSTagsValueShouldBeN("Food", 4); //                       // * The sum of all "Food" tags value should be 4.
  await context.endTheCurrentMoon(); //                                             // * End the current moon.
  await context.thereShouldBeNSCards(4, "Villager"); //                             // * There should be 4 "Villager" cards.
  await context.thereShouldBeNSCards(0, "Meat"); //                                 // * There should be 0 "Meat" cards.
  await context.theSumOfAllSTagsValueShouldBeN("Eats", 4); //                       // * The sum of all "Eats" tags value should be 4.
  await context.theSumOfAllSTagsValueShouldBeN("Food", 0); //                       // * The sum of all "Food" tags value should be 0.

  await context.afterTest();
  await runWhenTestSuccessful();
});
