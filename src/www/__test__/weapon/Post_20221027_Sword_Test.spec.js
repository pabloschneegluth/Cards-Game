import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221027_Sword_Context } from "./Post_20221027_Sword_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-10-27_sword.md", async () => {
  await runBeforeTestStarts(
    "weapon/2022-10-27_sword",
    "0d98beb4a9d1d0e2c862793ccb765a1d"
  );

  const context = new Post_20221027_Sword_Context();
  await context.beforeTest();

  // # Sword                                                                                                        // # Sword

  // ## How to create the sword                                                                                     // ## How to create the sword
  await context.givenANewGameWithAStackOfNSNSNSAndNSCards(
    1,
    "Build Idea",
    1,
    "Villager",
    1,
    "Wood",
    2,
    "Iron"
  ); // // * Given a new game with a stack of 1 "Build Idea", 1 "Villager", 1 "Wood" and 2 "Iron" cards.
  await context.endTheCurrentMoon(); //                                                                             // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSNSCards(
    1,
    1,
    "Build Idea",
    1,
    "Villager",
    1,
    "Sword"
  ); //                // * There should be 1 stacks of 1 "Build Idea", 1 "Villager", 1 "Sword" cards

  await context.afterTest();
  await runWhenTestSuccessful();
});
