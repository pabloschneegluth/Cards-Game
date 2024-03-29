import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221114_WolfKillVillager_Context } from "./Post_20221114_WolfKillVillager_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-14_wolf_kill_villager.md", async () => {
  await runBeforeTestStarts(
    "animal/2022-11-14_wolf_kill_villager",
    "eb8fc1c21e4256efb1b645abdf11e237"
  );

  const context = new Post_20221114_WolfKillVillager_Context();
  await context.beforeTest();

  // # Wolf Kill Villager!                              // # Wolf Kill Villager!
  // # How wolf kills Villager?                         // # How wolf kills Villager?
  await context.givenThereAreNSCards(1, "Wolf"); //     // * Given there are 1 "Wolf" cards.
  await context.givenThereAreNSCards(1, "Villager"); // // * Given there are 1 "Villager" cards.
  await context.endTheCurrentMoon(); //                 // * End the current moon.
  await context.thereShouldBeNSCards(0, "Villager"); // // * There should be 0 "Villager" cards.
  await context.thereShouldBeNSCards(2, "Wolf"); //     // * There should be 2 "Wolf" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
