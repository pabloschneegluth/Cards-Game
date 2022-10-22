import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221020_WolfCallsWolf_Context } from "./Post_20221020_WolfCallsWolf_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-10-20_wolf_calls_wolf.md", async () => {
  await runBeforeTestStarts(
    "2022-10-20_wolf_calls_wolf",
    "312f5c322bb5c98b5c4dd095ee933f4f"
  );

  const context = new Post_20221020_WolfCallsWolf_Context();
  await context.beforeTest();

  // # Wolf Calls wolf                              // # Wolf Calls wolf
  await context.givenThereAreNSCards(1, "Wolf"); // // * Given there are 1 "Wolf" cards.
  await context.endTheCurrentMoon(); //             // * End the current moon.
  await context.thereShouldBeNSCards(2, "Wolf"); // // * There should be 2 "Wolf" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
