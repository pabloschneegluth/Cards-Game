import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221105_Flint_Context } from "./Post_20221105_Flint_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-05_flint.md", async () => {
  await runBeforeTestStarts(
    "mineral/2022-11-05_flint",
    "25e18333dbff37a9b7408a67795eb2c5"
  );

  const context = new Post_20221105_Flint_Context();
  await context.beforeTest();

  // # FLINT                                                                                         // # FLINT

  // ## HOW TO ACQUIRE FLINT                                                                         // ## HOW TO ACQUIRE FLINT
  await context.givenANewGameWithAStackOfNSNSAndNSCards(
    1,
    "Villager",
    1,
    "Stone",
    1,
    "Pickaxe"
  ); // // * Given a new game with a stack of 1 "Villager", 1 "Stone" and 1 "Pickaxe" cards
  await context.endTheCurrentMoon(); //                                                              // * End the current moon
  await context.thereShouldBeNStackOfNSNSAndNSCards(
    1,
    1,
    "Villager",
    1,
    "Pickaxe",
    2,
    "Flint"
  ); //  // * There should be 1 stack of 1 "Villager", 1 "Pickaxe" and 2 "Flint" cards

  await context.afterTest();
  await runWhenTestSuccessful();
});
