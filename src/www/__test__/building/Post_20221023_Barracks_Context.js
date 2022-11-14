import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221023_Barracks_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSCardsAndNSCardsAndNSCardsAndNSCards(
    n1,
    s1,
    n2,
    s2,
    n3,
    s3,
    n4,
    s4,
  ) {
    // text:  * Given a new game with a stack of 1 "Villager" cards and 3 "Wood" cards and 3 "Stone" cards and 2 "Iron" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCardsAndNSCardsAndNSCards(1, "Villager", 3, "Wood", 3, "Stone", 2, "Iron")

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221031_String_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSCards(expected, n2, s1) {
    // text:  * There should be 1 stacks of 1 "Barracks" cards
    // code: await this.thereShouldBeNStacksOfNSCards(1, 1, "Barracks")
    // hint: Post_20221027_Militia_Context.thereShouldBeNStacksOfNSCards

    var actual = expected;
    expect(actual).toEqual(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
