import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221104_Book_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSCardsNSAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 5 "Paper" cards, 1 "Build Idea" and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsNSAndNSCards(5, "Paper", 1, "Build Idea", 1, "Villager")
    // hint: Post_20221106_Lake_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();
  }

  async givenANewGameWithAStackOfNSNSCardsAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 5 "Paper" cards, 1 "Build Idea" and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsNSAndNSCards(5, "Paper", 1, "Build Idea", 1, "Villager")
    // hint: Post_20221114_Chicken_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Build Idea" idea at level 3 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Build Idea", 3, 0)
    // hint: Post_20221106_Lake_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();


    // hint: Post_20221114_Chicken_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221027_Sword_Context.endTheCurrentMoon

    await waitForEndMoon();

    // hint: Post_20221020_WolfCallsWolf_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Book" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Book")
    // hint: Post_20221106_Lake_Context.thereShouldBeNStacksOfNSNSAndNSCards

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
