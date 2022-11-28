import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221103_Chest_Context } from "./Post_20221103_Chest_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-03_chest.md", async () => {
  await runBeforeTestStarts(
    "artifact/2022-11-03_chest",
    "53e9dea61490f638825560a822193892"
  );

  const context = new Post_20221103_Chest_Context();
  await context.beforeTest();

  // # Chest                                                                                                   // # Chest

  // ## How to find a Chest?                                                                                   // ## How to find a Chest?
  await context.givenANewGameWithAStackOfNSCardsAndNSCards(
    1,
    "Woods Stroll Idea",
    1,
    "Villager"
  ); //          // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
  await context.theSMayCreateASCard("Woods Stroll Idea", "Chest"); //                                          // * The "Woods Stroll Idea" may create a "Chest" card.
  await context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(
    "Chest",
    "Woods Stroll Idea"
  ); //           // * Given that the odds are that we will get a "Chest" card from the "Woods Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                        // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSAndNSCards(
    1,
    1,
    "Woods Stroll Idea",
    1,
    "Villager",
    1,
    "Chest"
  ); // // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Chest" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
