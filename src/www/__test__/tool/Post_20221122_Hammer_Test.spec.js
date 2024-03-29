import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221122_Hammer_Context } from "./Post_20221122_Hammer_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-22_hammer.md", async () => {
  await runBeforeTestStarts(
    "tool/2022-11-22_hammer",
    "7b0588a8cff6ea45cd3b6f8fbb218f3b"
  );

  const context = new Post_20221122_Hammer_Context();
  await context.beforeTest();

  // # Hammer                                                                                                        // # Hammer

  // ## How to create a hammer                                                                                       // ## How to create a hammer
  await context.givenANewGameWithAStackOfNSNSNSAndNSCards(
    1,
    "Build Idea",
    1,
    "Villager",
    2,
    "Iron",
    2,
    "Stone"
  ); // // * Given a new game with a stack of 1 "Build Idea", 1 "Villager" , 2 "Iron" and 2 "Stone" cards.
  await context.endTheCurrentMoon(); //                                                                              // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSAndNSCards(
    1,
    1,
    "Build Idea",
    1,
    "Villager",
    1,
    "Hammer"
  ); //             // * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Hammer" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
