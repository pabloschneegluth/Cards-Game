import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221013_Wood_Context } from "./Post_20221013_Wood_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-10-13_wood.md", async () => {
  await runBeforeTestStarts(
    "material/2022-10-13_wood",
    "1967b7c683917c39434f841f7204f193"
  );

  const context = new Post_20221013_Wood_Context();
  await context.beforeTest();

  // # Wood                                                                                                   // # Wood
  // ### Finding more things in the woods                                                                     // ### Finding more things in the woods
  await context.givenANewGameWithAStackOfNSCardsAndNSCards(
    1,
    "Woods Stroll Idea",
    1,
    "Villager"
  ); //         // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
  await context.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 2, 0); //                             // * Given there is the "Woods Stroll Idea" idea at level 2 and 0 XP.
  await context.theSMayCreateASCard("Woods Stroll Idea", "Wood"); //                                          // * The "Woods Stroll Idea" may create a "Wood" card.
  await context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(
    "Wood",
    "Woods Stroll Idea"
  ); //           // * Given that the odds are that we will get a "Wood" card from the "Woods Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                       // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSAndNSCards(
    1,
    1,
    "Woods Stroll Idea",
    1,
    "Villager",
    1,
    "Wood"
  ); // // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Wood" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
