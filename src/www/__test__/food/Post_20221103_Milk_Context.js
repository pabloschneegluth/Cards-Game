import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { queryAllStackDigestByCardNames } from "../stack/queries";
import { Names } from "../util/Names";

export class Post_20221103_Milk_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Cow" cards and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Cow", 1, "Villager")
    // hint: Post_20221031_String_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221031_String_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(
    expected,
    count1,
    name1,
    count2,
    name2,
    count3,
    name3,
  ) {
    // text:  * There should be 1 stacks of 1 "Milk", 1 "Cow" and 1 "Villager" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Milk", 1, "Cow", 1, "Villager")
    // hint: Post_20221031_String_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
