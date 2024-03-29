import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221110_FindingThingsInLake_Context } from "./Post_20221110_FindingThingsInLake_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-10_finding_things_in_lake.md", async () => {
  await runBeforeTestStarts(
    "location/2022-11-10_finding_things_in_lake",
    "fe8dec1b3beeb501b1531ccd5e2a52c9"
  );

  const context = new Post_20221110_FindingThingsInLake_Context();
  await context.beforeTest();

  // # Finding things in Lake                                                                                                                  // # Finding things in Lake

  // ## | $CardName |                                                                                                                          // ## | $CardName |

  // ## |-----------|                                                                                                                          // ## |-----------|

  // ## | Fish      |                                                                                                                          // ## | Fish      |
  await context.givenANewGameWithAStackOfNSNSNSAndNSCards(
    1,
    "Lake Stroll Idea",
    1,
    "Lake",
    1,
    "Villager",
    1,
    "Fishing Rod"
  ); //               // * Given a new game with a stack of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager" and 1 "Fishing Rod" cards.
  await context.theSMayCreateASCard("Lake Stroll Idea", "Fish"); //                                                                            // * The "Lake Stroll Idea" may create a "Fish" card.
  await context.givenThatTheOddsAreThatWeWillGetASFromTheSCard(
    "Fish",
    "Lake Stroll Idea"
  ); //                                                 // * Given that the odds are that we will get a "Fish" from the "Lake Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                                                        // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSNSNSAndNSCards(
    1,
    1,
    "Lake Stroll Idea",
    1,
    "Lake",
    1,
    "Villager",
    1,
    "Fishing Rod",
    1,
    "Fish"
  ); //  // * There should be 1 stacks of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager", 1 "Fishing Rod" and 1 "Fish" cards.
  // # Finding things in Lake                                                                                                                  // # Finding things in Lake

  // ## | $CardName |                                                                                                                          // ## | $CardName |

  // ## |-----------|                                                                                                                          // ## |-----------|

  // ## | Shark     |                                                                                                                          // ## | Shark     |
  await context.givenANewGameWithAStackOfNSNSNSAndNSCards(
    1,
    "Lake Stroll Idea",
    1,
    "Lake",
    1,
    "Villager",
    1,
    "Fishing Rod"
  ); //               // * Given a new game with a stack of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager" and 1 "Fishing Rod" cards.
  await context.theSMayCreateASCard("Lake Stroll Idea", "Shark"); //                                                                           // * The "Lake Stroll Idea" may create a "Shark" card.
  await context.givenThatTheOddsAreThatWeWillGetASFromTheSCard(
    "Shark",
    "Lake Stroll Idea"
  ); //                                                // * Given that the odds are that we will get a "Shark" from the "Lake Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                                                        // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSNSNSAndNSCards(
    1,
    1,
    "Lake Stroll Idea",
    1,
    "Lake",
    1,
    "Villager",
    1,
    "Fishing Rod",
    1,
    "Shark"
  ); // // * There should be 1 stacks of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager", 1 "Fishing Rod" and 1 "Shark" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
