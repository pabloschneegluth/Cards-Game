import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221114_Chicken_Context } from "./Post_20221114_Chicken_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-14_chicken.md", async () => {
  await runBeforeTestStarts(
    "animal/2022-11-14_chicken",
    "64b073998c6621182178a84ce83c5065"
  );

  const context = new Post_20221114_Chicken_Context();
  await context.beforeTest();

  // # Chicken                                                                                                   // # Chicken

  // ## How to find a Chicken                                                                                    // ## How to find a Chicken
  await context.givenANewGameWithAStackOfNSCardsAndNSCards(
    1,
    "Woods Stroll Idea",
    1,
    "Villager"
  ); //            // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
  await context.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 4, 0); //                                // * Given there is the "Woods Stroll Idea" idea at level 4 and 0 XP.
  await context.theSMayCreateASCard("Woods Stroll Idea", "Chicken"); //                                          // * The "Woods Stroll Idea" may create a "Chicken" card.
  await context.givenThatTheOddsAreThatWeWillGetASFromTheSCard(
    "Chicken",
    "Woods Stroll Idea"
  ); //               // * Given that the odds are that we will get a "Chicken" from the "Woods Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                          // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSAndNSCards(
    1,
    1,
    "Woods Stroll Idea",
    1,
    "Villager",
    1,
    "Chicken"
  ); // // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Chicken" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
