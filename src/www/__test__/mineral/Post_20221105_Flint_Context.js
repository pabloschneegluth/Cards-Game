import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221105_Flint_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithNStackOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) {
    // text:  * Given a new game with 1 stack of 1 "Villager", 1 "Stone" and 1 "Pickaxe" cards
    // code: await this.givenANewGameWithNStackOfNSNSAndNSCards(1, 1, "Villager", 1, "Stone", 1, "Pickaxe")
    // hint: Post_20221027_Sword_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithNStackOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221103_Creeper_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNStackOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stack of 1 "Villager", 1 "Pickaxe" and 2 "Flint" cards
    // code: await this.thereShouldBeNStackOfNSNSAndNSCards(1, 1, "Villager", 1, "Pickaxe", 2, "Flint")
    // hint: Post_20221019_Farm_Context.thereShouldBeNStackOfNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStackOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
