import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221013_WoodHouse_Context } from "./Post_20221013_WoodHouse_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-10-13_wood_house.md", async () => {
  await runBeforeTestStarts(
    "building/2022-10-13_wood_house",
    "72f502681469f905ca2388c948d55905"
  );

  const context = new Post_20221013_WoodHouse_Context();
  await context.beforeTest();

  // # Wood House                                                                                                      // # Wood House

  // ## How to build a Wood House                                                                                      // ## How to build a Wood House
  await context.givenANewGameWithAStackOfNSCardsNSCardsAndNSCards(
    1,
    "Build Idea",
    1,
    "Villager",
    4,
    "Wood"
  ); //       // * Given a new game with a stack of 1 "Build Idea" cards, 1 "Villager" cards and 4 "Wood" cards
  await context.endTheCurrentMoon(); //                                                                                // * End the current moon.
  await context.thereShouldBeNStacksOfNSCardsNSCardsAndNSCards(
    1,
    1,
    "Build Idea",
    1,
    "Villager",
    1,
    "Wood House"
  ); // // * There should be 1 stacks of 1 "Build Idea" cards, 1 "Villager" cards and 1 "Wood House" cards

  await context.afterTest();
  await runWhenTestSuccessful();
});
