import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221207_HunterKillGrizzly_Context } from "./Post_20221207_HunterKillGrizzly_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-12-07_hunter_kill_grizzly.md", async () => {
  await runBeforeTestStarts(
    "animal/2022-12-07_hunter_kill_grizzly",
    "f4aff42e8c0f7eb805c5b102e399c523"
  );

  const context = new Post_20221207_HunterKillGrizzly_Context();
  await context.beforeTest();

  // # Hunter Kill Grizzly                             // # Hunter Kill Grizzly

  // ## How to kill a Grizzly?                         // ## How to kill a Grizzly?
  await context.givenThereAreNSCards(1, "Hunter"); //  // * Given there are 1 "Hunter" cards.
  await context.givenThereAreNSCards(1, "Grizzly"); // // * Given there are 1 "Grizzly" cards.
  await context.endTheCurrentMoon(); //                // * End the current moon.
  await context.thereShouldBeNSCards(0, "Grizzly"); // // * There should be 0 "Grizzly" cards.
  await context.thereShouldBeNSCards(1, "Hunter"); //  // * There should be 1 "Hunter" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
