import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221124_ArcherWithArrowKill_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithAStackOfNSNSAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 1 "Archer", 1 "Arrow" and 1 "Wolf" cards.
    // code: await this.givenANewGameWithAStackOfNSNSAndNSCards(1, "Archer", 1, "Arrow", 1, "Wolf")
    // hint: Post_20221105_Flint_Context.givenANewGameWithAStackOfNSNSAndNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithAStackOfNSNSAndNSCards(n1, s1, n2, s2, n3, s3) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeAStackWithNSNSAndNSCards(expected, s1, n2, s2, n3, s3) {
    // text:  * There should be a stack with 1 "Archer", 0 "Arrow" and 0 "Wolf" cards.
    // code: await this.thereShouldBeAStackWithNSNSAndNSCards(1, "Archer", 0, "Arrow", 0, "Wolf")
    // hint: Post_20221119_HowToKillAnimals_Context.thereShouldBeAStackWithNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeAStackWithNSNSAndNSCards(expected, s1, n2, s2, n3, s3) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
