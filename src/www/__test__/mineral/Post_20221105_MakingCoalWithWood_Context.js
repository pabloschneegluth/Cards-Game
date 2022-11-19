import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221105_MakingCoalWithWood_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithNStackOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) {
    // text:  * Given a new game with 1 stack of 1 "Villager", 1 "Flint" and 1 "Wood" cards
    // code: await this.givenANewGameWithNStackOfNSNSAndNSCards(1, 1, "Villager", 1, "Flint", 1, "Wood")
    // hint: Post_20221105_Flint_Context.givenANewGameWithAStackOfNSNSAndNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithNStackOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Villager", 1 "Flint" and 2 "Coal" cards
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Villager", 1, "Flint", 2, "Coal")
    // hint: Post_20221106_Lake_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
