import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221020_Bone_Context } from "./Post_20221020_Bone_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-10-20_bone.md", async () => {
  await runBeforeTestStarts(
    "material\\2022-10-20_bone",
    "3e2dc4940fcc98d8162b4e8b757253bb"
  );

  const context = new Post_20221020_Bone_Context();
  await context.beforeTest();

  // # Bone                                                                                                   // # Bone
  // # How to find a Bone?                                                                                    // # How to find a Bone?
  await context.givenANewGameWithAStackOfNSCardsAndNSCards(
    1,
    "Woods Stroll Idea",
    1,
    "Villager"
  ); //         // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
  await context.theSMayCreateASCard("Woods Stroll Idea", "Bone"); //                                          // * The "Woods Stroll Idea" may create a "Bone" card.
  await context.givenThatTheOddsAreThatWeWillGetASFromTheSCard(
    "Bone",
    "Woods Stroll Idea"
  ); //               // * Given that the odds are that we will get a "Bone" from the "Woods Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                       // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSAndNSCards(
    1,
    1,
    "Woods Stroll Idea",
    1,
    "Villager",
    1,
    "Bone"
  ); // // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Bone" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
