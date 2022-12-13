import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221128_Dwarf_Context } from "./Post_20221128_Dwarf_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-28_dwarf.md", async () => {
  await runBeforeTestStarts(
    "villager/2022-11-28_dwarf",
    "296bcdc5a0ca64c8678965150b60f69f"
  );

  const context = new Post_20221128_Dwarf_Context();
  await context.beforeTest();

  // # Dwarf                                                                    // # Dwarf

  // ## How to transform a dwarf                                                // ## How to transform a dwarf
  await context.givenANewGameWithAStackOfNSNSCards(1, "Villager", 1, "Axe"); // // * Given a new game with a stack of 1 "Villager", 1 "Axe" cards.
  await context.endTheCurrentMoon(); //                                         // * End the current moon.
  await context.thereShouldBeNStacksOfNSCards(1, 1, "Dwarf"); //                // * There should be 1 stacks of 1 "Dwarf" cards

  await context.afterTest();
  await runWhenTestSuccessful();
});
