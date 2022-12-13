import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221128_Dwarf_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithAStackOfNSNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Villager", 1 "Axe" cards.
    // code: await this.givenANewGameWithAStackOfNSNSCards(1, "Villager", 1, "Axe")
    // hint: Post_20221106_Archer_Context.givenANewGameWithAStackOfNSNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithAStackOfNSNSCards(n1, s1, n2, s2) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221119_Meat_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNStacksOfNSCards(expected, n2, s1) {
    // text:  * There should be 1 stacks of 1 "Dwarf" cards
    // code: await this.thereShouldBeNStacksOfNSCards(1, 1, "Dwarf")
    // hint: Post_20221106_Archer_Context.thereShouldBeNStacksOfNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStacksOfNSCards(expected, n2, s1) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
