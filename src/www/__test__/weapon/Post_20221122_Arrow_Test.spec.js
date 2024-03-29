import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221122_Arrow_Context } from "./Post_20221122_Arrow_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-22_arrow.md", async () => {
  await runBeforeTestStarts(
    "weapon/2022-11-22_arrow",
    "6e5aa29549c993722951d32897a8b5af"
  );

  const context = new Post_20221122_Arrow_Context();
  await context.beforeTest();

  // # Arrow                                                                                                                   // # Arrow

  // ## How to create an Arrow?                                                                                                // ## How to create an Arrow?
  await context.givenANewGameWithAStackOfNSNSCardsNSCardsAndNSCards(
    1,
    "Build Idea",
    1,
    "Villager",
    1,
    "Stone",
    1,
    "Wood"
  ); // // * Given a new game with a stack of 1 "Build Idea", 1 "Villager" cards, 1 "Stone" cards and 1 "Wood" cards.
  await context.givenThereIsTheSIdeaAtLevelNAndNXp("Build Idea", 1, 0); //                                                     // * Given there is the "Build Idea" idea at level 1 and 0 XP.
  await context.endTheCurrentMoon(); //                                                                                        // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSAndNSCards(
    1,
    1,
    "Build Idea",
    1,
    "Villager",
    1,
    "Arrow"
  ); //                        // * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Arrow" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
