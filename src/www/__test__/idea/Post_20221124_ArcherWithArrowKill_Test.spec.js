import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221124_ArcherWithArrowKill_Context } from "./Post_20221124_ArcherWithArrowKill_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-24_archer_with_arrow_kill.md", async () => {
  await runBeforeTestStarts(
    "idea\\2022-11-24_archer_with_arrow_kill",
    "6e9058b3f4fd79f0bc06693c19064fd1"
  );

  const context = new Post_20221124_ArcherWithArrowKill_Context();
  await context.beforeTest();

  // # Archer with arrow kill                                                                   // # Archer with arrow kill

  // ## How archer kills?                                                                       // ## How archer kills?
  await context.givenANewGameWithAStackOfNSNSAndNSCards(
    1,
    "Archer",
    1,
    "Arrow",
    1,
    "Wolf"
  ); // // * Given a new game with a stack of 1 "Archer", 1 "Arrow" and 1 "Wolf" cards.
  await context.endTheCurrentMoon(); //                                                         // * End the current moon.
  await context.thereShouldBeAStackWithNSNSAndNSCards(
    1,
    "Archer",
    0,
    "Arrow",
    0,
    "Wolf"
  ); //   // * There should be a stack with 1 "Archer", 0 "Arrow" and 0 "Wolf" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
