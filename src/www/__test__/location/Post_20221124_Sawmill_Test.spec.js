import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221124_Sawmill_Context } from "./Post_20221124_Sawmill_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-24_sawmill.md", async () => {
  await runBeforeTestStarts(
    "location\\2022-11-24_sawmill",
    "3559223128b51607a0dd5ca1a34578ce"
  );

  const context = new Post_20221124_Sawmill_Context();
  await context.beforeTest();

  // # Sawmill                                                                                                   // # Sawmill

  // ## How to find a Sawmill?                                                                                   // ## How to find a Sawmill?
  await context.givenANewGameWithAStackOfNSCardsAndNSCards(
    1,
    "Woods Stroll Idea",
    1,
    "Villager"
  ); //            // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
  await context.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 5, 0); //                                // * Given there is the "Woods Stroll Idea" idea at level 5 and 0 XP.
  await context.theSMayCreateASCard("Woods Stroll Idea", "Sawmill"); //                                          // * The "Woods Stroll Idea" may create a "Sawmill" card.
  await context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(
    "Sawmill",
    "Woods Stroll Idea"
  ); //           // * Given that the odds are that we will get a "Sawmill" card from the "Woods Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                          // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSAndNSCards(
    1,
    1,
    "Woods Stroll Idea",
    1,
    "Villager",
    1,
    "Sawmill"
  ); // // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Sawmill" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
