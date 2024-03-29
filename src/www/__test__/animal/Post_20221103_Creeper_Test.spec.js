import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221103_Creeper_Context } from "./Post_20221103_Creeper_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-11-03_creeper.md", async () => {
  await runBeforeTestStarts(
    "animal/2022-11-03_creeper",
    "9a7221075471ff734bb5b8bef4fca64c"
  );

  const context = new Post_20221103_Creeper_Context();
  await context.beforeTest();

  // # Creeper!                                                                                                       // # Creeper!

  // ## How to find a creeper?                                                                                        // ## How to find a creeper?
  await context.givenANewGameWithAStackOfNSCardsAndNSCard(
    1,
    "Woods Stroll Idea",
    1,
    "Villager"
  ); //                  // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" card.
  await context.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 5, 0); //                                     // * Given there is the "Woods Stroll Idea" idea at level 5 and 0 XP.
  await context.theSMayCreateASCard("Woods Stroll Idea", "Creeper"); //                                               // * The "Woods Stroll Idea" may create a "Creeper" card.
  await context.givenThatTheOddsAreThatWeWillFindASCardFromTheSCard(
    "Creeper",
    "Woods Stroll Idea"
  ); //               // * Given that the odds are that we will find a "Creeper" card from the "Woods Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                               // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSCardsAndNSCards(
    1,
    1,
    "Woods Stroll Idea",
    1,
    "Villager",
    1,
    "Creeper"
  ); // // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" cards and 1 "Creeper" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
