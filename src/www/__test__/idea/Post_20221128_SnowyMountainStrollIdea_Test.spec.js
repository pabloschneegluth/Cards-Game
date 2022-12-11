import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221128_SnowyMountainStrollIdea_Context } from "./Post_20221128_SnowyMountainStrollIdea_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-28_snowy_mountain_stroll_idea.md", async () => {
  await runBeforeTestStarts(
    "idea\\2022-11-28_snowy_mountain_stroll_idea",
    "0136f563ec80ae4baea8f8eed7213b96"
  );

  const context = new Post_20221128_SnowyMountainStrollIdea_Context();
  await context.beforeTest();

  // # Snowy Mountain Stroll idea                                                                 // # Snowy Mountain Stroll idea

  // ## How to get Snowy Mountain stroll idea?                                                    // ## How to get Snowy Mountain stroll idea?
  await context.givenANewGameWithAStackOfNSCardsAndNSCard(
    1,
    "Snowy Mountain",
    1,
    "Villager"
  ); // // * Given a new game with a stack of 1 "Snowy Mountain" cards and 1 "Villager" card.
  await context.endTheCurrentMoon(); //                                                           // * End the current moon.
  await context.thereShouldBeTheSIdea("Snowy Mountain Stroll Idea"); //                           // * There should be the "Snowy Mountain Stroll Idea" idea.
  await context.theSShouldHaveLevelNAndNXp("Snowy Mountain Stroll Idea", 1, 0); //                // * The "Snowy Mountain Stroll Idea" should have level 1 and 0 XP.

  await context.afterTest();
  await runWhenTestSuccessful();
});
