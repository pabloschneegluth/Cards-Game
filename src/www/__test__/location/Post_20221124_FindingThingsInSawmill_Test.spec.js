import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221124_FindingThingsInSawmill_Context } from "./Post_20221124_FindingThingsInSawmill_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-24_finding_things_in_sawmill.md", async () => {
  await runBeforeTestStarts(
    "location\\2022-11-24_finding_things_in_sawmill",
    "364a27190016ef349270f009fa6ab463"
  );

  const context = new Post_20221124_FindingThingsInSawmill_Context();
  await context.beforeTest();

  // # Finding things in Sawmill                                                                                                // # Finding things in Sawmill

  // ## | $CardName |                                                                                                           // ## | $CardName |

  // ## |-----------|                                                                                                           // ## |-----------|

  // ## | Wood      |                                                                                                           // ## | Wood      |
  await context.givenANewGameWithAStackOfNSNSAndNSCards(
    1,
    "Sawmill Stroll Idea",
    1,
    "Sawmill",
    1,
    "Villager"
  ); //              // * Given a new game with a stack of 1 "Sawmill Stroll Idea", 1 "Sawmill" and 1 "Villager" cards.
  await context.theSMayCreateASCard("Sawmill Stroll Idea", "Wood"); //                                                          // * The "Sawmill Stroll Idea" may create a "Wood" card.
  await context.givenThatTheOddsAreThatWeWillGetASFromTheSCard(
    "Wood",
    "Sawmill Stroll Idea"
  ); //                               // * Given that the odds are that we will get a "Wood" from the "Sawmill Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                                         // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSNSAndNSCards(
    1,
    1,
    "Sawmill Stroll Idea",
    1,
    "Sawmill",
    1,
    "Villager",
    2,
    "Wood"
  ); // // * There should be 1 stacks of 1 "Sawmill Stroll Idea", 1 "Sawmill", 1 "Villager" and 2 "Wood" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
