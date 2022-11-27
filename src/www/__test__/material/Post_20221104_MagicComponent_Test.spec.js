import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221104_MagicComponent_Context } from "./Post_20221104_MagicComponent_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-04_magic_component.md", async () => {
  await runBeforeTestStarts(
    "material/2022-11-04_magic_component",
    "6004616e082e250708316b0f4d835313"
  );

  const context = new Post_20221104_MagicComponent_Context();
  await context.beforeTest();

  // # Magic Component                                                                         // # Magic Component

  // ## How to obtain Magic Component?                                                         // ## How to obtain Magic Component?
  await context.givenANewGameWithAStackOfNSCardsAndNSCards(
    1,
    "Chest",
    1,
    "Key"
  ); //           // * Given a new game with a stack of 1 "Chest" cards and 1 "Key" cards.
  await context.theSMayCreateASCard("Chest", "Magic Book"); //                                 // * The "Chest" may create a "Magic Book" card.
  await context.givenThatTheOddsAreThatWeWillGetASFromTheSCard(
    "Magic Component",
    "Chest"
  ); // // * Given that the odds are that we will get a "Magic Component" from the "Chest" card.
  await context.endTheCurrentMoon(); //                                                        // * End the current moon.
  await context.thereShouldBeNStacksOfNSCards(1, 1, "Magic Component"); //                     // * There should be 1 stacks of 1 "Magic Component" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
