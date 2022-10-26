import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221013_StoneHouse_Context } from "./Post_20221013_StoneHouse_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-10-13_stone_house.md", async () => {
  await runBeforeTestStarts(
    "2022-10-13_stone_house",
    "01b41c689763ecbb7474da4cb3dfc435"
  );

  const context = new Post_20221013_StoneHouse_Context();
  await context.beforeTest();

  // # Stone House                                                                                                      // # Stone House

  // ## How to build a Stone House                                                                                      // ## How to build a Stone House
  await context.givenANewGameWithAStackOfNSCardsNSCardsAndNSCards(
    1,
    "Build Idea",
    1,
    "Villager",
    4,
    "Stone"
  ); //       // * Given a new game with a stack of 1 "Build Idea" cards, 1 "Villager" cards and 4 "Stone" cards
  await context.endTheCurrentMoon(); //                                                                                 // * End the current moon.
  await context.thereShouldBeNStacksOfNSCardsNSCardsAndNSCards(
    1,
    1,
    "Build Idea",
    1,
    "Villager",
    1,
    "Stone House"
  ); // // * There should be 1 stacks of 1 "Build Idea" cards, 1 "Villager" cards and 1 "Stone House" cards

  await context.afterTest();
  await runWhenTestSuccessful();
});
