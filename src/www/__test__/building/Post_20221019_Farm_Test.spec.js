import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221019_Farm_Context } from "./Post_20221019_Farm_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-10-19_farm.md", async () => {
  await runBeforeTestStarts(
    "building/2022-10-19_farm",
    "ce6fee9e765b7ccb95f3a8a5f56b27de"
  );

  const context = new Post_20221019_Farm_Context();
  await context.beforeTest();

  // # farm                                                                                                                         // # farm
  await context.thereShouldBeNSCard(0, "Farm"); //                                                                                  // * there should be 0 "Farm" card.
  await context.givenThereIsNStackOfNSAtLevelNAndNXpNSNSNSCards(
    1,
    1,
    "Build Idea",
    2,
    6,
    1,
    "Villager",
    1,
    "Stone",
    2,
    "Wood"
  ); // // * given there is 1 stack of 1 "Build Idea" at level 2 and 6 XP, 1 "Villager", 1 "Stone", 2 "Wood" cards
  await context.endTheCurrentMoon(); //                                                                                             // * end the current moon.
  await context.thereShouldBeNStackOfNSNSAndNSCards(
    1,
    1,
    "Build Idea",
    1,
    "Villager",
    1,
    "Farm"
  ); //                               // * there should be 1 stack of 1 "Build Idea", 1 "Villager" and 1 "Farm" cards

  // ## how the farm works                                                                                                          // ## how the farm works
  await context.givenANewEmptyGame(); //                                                                                            // * Given a new empty game
  await context.givenThereIsNStackOfNSCardsNSCardsAndNSCardsOrAnotherPlant(
    1,
    1,
    "Farm",
    1,
    "Villager",
    1,
    "Berry Bush"
  ); //        // * Given there is 1 stack of 1 "Farm" cards, 1 "Villager" cards and 1 "Berry Bush" cards or another plant.
  await context.endTheCurrentMoon(); //                                                                                             // * end the current moon.
  await context.thereShouldBeNStackOfNSCardsNSCardsNSAndNSCards(
    1,
    1,
    "Farm",
    1,
    "Villager",
    1,
    "Berry Bush",
    5,
    "Berry"
  ); //       // * There should be 1 stack of 1 "Farm" cards, 1 "Villager" cards, 1 "Berry Bush" and 5 "Berry" cards

  await context.afterTest();
  await runWhenTestSuccessful();
});
