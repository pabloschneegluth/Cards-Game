import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221020_BuildIdea_Context } from "./Post_20221020_BuildIdea_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-10-20_build_idea.md", async () => {
  await runBeforeTestStarts(
    "2022-10-20_build_idea",
    "36946fb8d34da167cd0389d52639888f"
  );

  const context = new Post_20221020_BuildIdea_Context();
  await context.beforeTest();

  // # Build idea                                                                                                     // # Build idea

  // ## How to acquire:                                                                                               // ## How to acquire:
  await context.givenANewGameWithAStackOfNSCardsNSCardAndNSCard(
    1,
    "Harvest Idea",
    1,
    "Villager",
    1,
    "Berr Bush"
  ); // // * Given a new game with a stack of 1 "Harvest Idea" cards, 1 "Villager" card and 1 "Berr Bush" card..
  await context.givenThereIsTheSIdeaAtLevelNAndNXp("Harvest Idea", 2, 19); //                                         // * Given there is the "Harvest Idea" idea at level 2 and 19 XP.
  await context.thereShouldBeNoSIdea("Build Idea"); //                                                                // * There should be no "Build Idea" idea.
  await context.endTheCurrentMoon(); //                                                                               // * End the current moon.
  await context.theSShouldHaveLevelNAndNXp("Harvest Idea", 3, 0); //                                                  // * The "Harvest Idea" should have level 3 and 0 XP.
  await context.thereShouldBeTheSIdea("Build Idea"); //                                                               // * There should be the "Build Idea" idea.
  await context.theSShouldHaveLevelNAndNXp("Build Idea", 1, 0); //                                                    // * The "Build Idea" should have level 1 and 0 XP.

  await context.afterTest();
  await runWhenTestSuccessful();
});
