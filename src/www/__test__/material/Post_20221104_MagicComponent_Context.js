import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221104_MagicComponent_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Chest" cards and 1 "Key" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Chest", 1, "Key")
    // hint: Post_20221106_Lake_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) is not implemented yet."
    );
  }

  async theSMayCreateASCard(s1, s2) {
    // text:  * The "Chest" may create a "Magic Book" card.
    // code: await this.theSMayCreateASCard("Chest", "Magic Book")
    // hint: Post_20221106_Lake_Context.theSMayCreateASCard

    throw new Error(
      "The method theSMayCreateASCard(s1, s2) is not implemented yet."
    );
  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Magic Component" from the "Chest" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Magic Component", "Chest")
    // hint: Post_20221024_Diamond_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

    await waitForReloadGame();

    throw new Error(
      "The method givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221027_Sword_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNStacksOfNSCards(expected, n2, s1) {
    // text:  * There should be 1 stacks of 1 "Magic Component" cards.
    // code: await this.thereShouldBeNStacksOfNSCards(1, 1, "Magic Component")
    // hint: Post_20221105_Wizard_Context.thereShouldBeNStacksOfNSCards

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
