import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221109_BreedFish_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGame() {
    // text:  * Given a new game.
    // code: await this.givenANewGame()

    await waitForReloadGame();

    throw new Error("The method givenANewGame() is not implemented yet.");
  }

  async givenAStackOfNSAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a stack of 1 "Villager" and 1 "Fish farm" cards.
    // code: await this.givenAStackOfNSAndNSCards(1, "Villager", 1, "Fish farm")

    await waitForReloadGame();

    throw new Error(
      "The method givenAStackOfNSAndNSCards(n1, s1, n2, s2) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221128_Grizzly_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeAStackOfNSNSAndNSCards(expected, s1, n2, s2, n3, s3) {
    // text:  * There should be a stack of 1 "Villager", 1 "Fish farm" and 1 "Fish" cards.
    // code: await this.thereShouldBeAStackOfNSNSAndNSCards(1, "Villager", 1, "Fish farm", 1, "Fish")
    // hint: Post_20221019_Farm_Context.thereShouldBeNStackOfNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeAStackOfNSNSAndNSCards(expected, s1, n2, s2, n3, s3) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
