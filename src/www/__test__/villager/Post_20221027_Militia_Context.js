import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221027_Militia_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Villager", 1 "Sword" cards.
    // code: await this.givenANewGameWithAStackOfNSNSCards(1, "Villager", 1, "Sword")

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221030_Cow_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNStacksOfNSCards(expected, n2, s1) {
    // text:  * There should be 1 stacks of 1 "Militia" cards
    // code: await this.thereShouldBeNStacksOfNSCards(1, 1, "Militia")

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
