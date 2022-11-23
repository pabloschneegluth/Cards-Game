import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221120_ChickenLaysEgg_Context } from "./Post_20221120_ChickenLaysEgg_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-20_chicken_lays_egg.md", async () => {
  await runBeforeTestStarts(
    "animal/2022-11-20_chicken_lays_egg",
    "9b301b1f2060b2ad422f7804d8893ab8"
  );

  const context = new Post_20221120_ChickenLaysEgg_Context();
  await context.beforeTest();

  // # Chicken lays egg                                                           // # Chicken lays egg

  // ## How a chicken lays an egg                                                 // ## How a chicken lays an egg
  await context.givenThereAreNSCards(1, "Chicken"); //                            // * Given there are 1 "Chicken" cards.
  await context.endTheCurrentMoon(); //                                           // * End the current moon.
  await context.thereShouldBeNStacksOfNSAndNSCards(1, 1, "Chicken", 1, "Egg"); // // * There should be 1 stacks of 1 "Chicken" and 1 "Egg" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
