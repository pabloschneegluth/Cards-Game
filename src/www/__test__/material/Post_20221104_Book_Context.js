import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221104_Book_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSNSCardsAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 1 "Build Idea", 1 "Villager" cards and 5 "Paper" cards.
    // code: await this.givenANewGameWithAStackOfNSNSCardsAndNSCards(1, "Build Idea", 1, "Villager", 5, "Paper")
    // hint: Post_20221104_Paper_Context.givenANewGameWithAStackOfNSNSCardsAndNSCards

    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Build Idea" idea at level 3 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Build Idea", 3, 0)
    // hint: Post_20221122_Arrow_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221106_Archer_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Book" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Book")
    // hint: Post_20221122_Arrow_Context.thereShouldBeNStacksOfNSNSAndNSCards

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
