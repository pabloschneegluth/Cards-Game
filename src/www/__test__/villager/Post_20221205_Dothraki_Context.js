import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221205_Dothraki_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Villager", 1 "Arakh" cards.
    // code: await this.givenANewGameWithAStackOfNSNSCards(1, "Villager", 1, "Arakh")
    // hint: Post_20221105_Wizard_Context.givenANewGameWithAStackOfNSNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221020_Pear_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSCards(expected, n2, s1) {
    // text:  * There should be 1 stacks of 1 "Dothraki" cards
    // code: await this.thereShouldBeNStacksOfNSCards(1, 1, "Dothraki")
    // hint: Post_20221105_Wizard_Context.thereShouldBeNStacksOfNSCards

    var names = Names.byNames(n2, s1).get();
    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
