import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221117_MilitiaKillCreeper_Context } from "./Post_20221117_MilitiaKillCreeper_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-17_militia_kill_creeper.md", async () => {
  await runBeforeTestStarts(
    "enemy/2022-11-17_militia_kill_creeper",
    "a4929e8c0f27886913e7c8fa046331aa"
  );

  const context = new Post_20221117_MilitiaKillCreeper_Context();
  await context.beforeTest();

  // # Militia Kill creeper                            // # Militia Kill creeper

  // ## How to kill a creeper?                         // ## How to kill a creeper?
  await context.givenThereAreNSCards(1, "Militia"); // // * Given there are 1 "Militia" cards.
  await context.givenThereAreNSCards(1, "Creeper"); // // * Given there are 1 "Creeper" cards.
  await context.endTheCurrentMoon(); //                // * End the current moon.
  await context.thereShouldBeNSCards(0, "Creeper"); // // * There should be 0 "Creeper" cards.
  await context.thereShouldBeNSCards(1, "Militia"); // // * There should be 1 "Militia" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
