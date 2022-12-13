import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221104_MagicBook_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSNSNSAndNSCards(
    n1,
    s1,
    n2,
    s2,
    n3,
    s3,
    n4,
    s4
  ) {
    // text:  * Given a new game with a stack of 1 "Build Idea", 1 "Villager", 1 "Magic Component" and 1 "Book" cards.
    // code: await this.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Build Idea", 1, "Villager", 1, "Magic Component", 1, "Book")
    // hint: Post_20221110_FindingThingsInLake_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Build Idea" idea at level 1 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Build Idea", 1, 0)
    // hint: Post_20221128_Grizzly_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221128_Grizzly_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Magic Book" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Magic Book")
    // hint: Post_20221114_Chicken_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var names = Names.byNames(n2, s1)
      .and(n3, s2)
      .and(n4, s3)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
