import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";

export class Post_20221119_HowToKillAnimals_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Militia" and 1 "Cow" cards.
    // code: await this.givenANewGameWithAStackOfNSAndNSCards(1, "Militia", 1, "Cow")
    // hint: Post_20221105_Flint_Context.givenANewGameWithAStackOfNSNSAndNSCards

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_Chicken_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeAStackWithNSAndNSCards(
    expected,
    name1,
    count2,
    name2,

  ) {
    // text:  * There should be a stack with 1 "Militia" and 3 "Meat" cards.
    // code: await this.thereShouldBeAStackWithNSAndNSCards(1, "Militia", 3, "Meat")

    var names = Names.byNames(expected, name1)
      .and(count2, name2)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
