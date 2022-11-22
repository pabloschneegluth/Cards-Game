import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221110_Bow_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithAStackOfNSNSNSAndNSCards(
    n1,
    s1,
    n2,
    s2,
    n3,
    s3,
    n4,
    s4
  ) {
    // text:  * Given a new game with a stack of 1 "Build Idea", 1 "Villager", 3 "Wood" and 2 "String" cards.
    // code: await this.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Build Idea", 1, "Villager", 3, "Wood", 2, "String")
    // hint: Post_20221031_FishingRod_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithAStackOfNSNSNSAndNSCards(n1, s1, n2, s2, n3, s3, n4, s4) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221120_Egg_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNStacksOfNSNSNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Villager", 1 "Bow" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Bow")
    // hint: Post_20221031_FishingRod_Context.thereShouldBeNStacksOfNSNSNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStacksOfNSNSNSCards(expected, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
