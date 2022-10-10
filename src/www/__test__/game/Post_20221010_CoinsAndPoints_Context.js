import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221010_CoinsAndPoints_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async enterTheGame() {
    // text:  * Enter the game.
    // code: await this.enterTheGame()
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.enterTheGame

    throw new Error("The method enterTheGame() is not implemented yet.");
  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 7 "Coins" cards.
    // code: await this.thereShouldBeNSCards(7, "Coins")
    // hint: Post_20221010_Points_Context.thereShouldBeNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNSCards(expected, s1) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
