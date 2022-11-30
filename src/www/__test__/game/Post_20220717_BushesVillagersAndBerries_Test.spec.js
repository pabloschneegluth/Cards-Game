import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20220717_BushesVillagersAndBerries_Context } from "./Post_20220717_BushesVillagersAndBerries_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-07-17_bushes_villagers_and_berries.md", async () => {
  await runBeforeTestStarts(
    "game/2022-07-17_bushes_villagers_and_berries",
    "187c6ace9199129be617f29fe9a5cfe4"
  );

  const context = new Post_20220717_BushesVillagersAndBerries_Context();
  await context.beforeTest();

  // # Bushes, Villagers and Berries                      // # Bushes, Villagers and Berries

  // ## The game                                          // ## The game
  await context.enterTheGame(); //                        // * Enter the game.
  await context.thereShouldBeNSCards(1, "Villager"); //   // * There should be 1 "Villager" cards.
  await context.thereShouldBeNSCards(1, "Berry Bush"); // // * There should be 1 "Berry Bush" cards.
  await context.thereShouldBeNSCards(1, "Berry"); //      // * There should be 1 "Berry" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
