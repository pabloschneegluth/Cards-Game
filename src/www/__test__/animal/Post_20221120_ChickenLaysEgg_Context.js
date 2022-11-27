import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";

export class Post_20221120_ChickenLaysEgg_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Chicken" cards.
    // code: await this.givenThereAreNSCards(1, "Chicken")
    // hint: Post_20220723_Ideas_Context.givenThereAreNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSAndNSCards(
    expected,
    count1,
    name1,
    count2,
    name2,
  ) {
    // text:  * There should be 1 stacks of 1 "Chicken" and 1 "Egg" cards.
    // code: await this.thereShouldBeNStacksOfNSAndNSCards(1, 1, "Chicken", 1, "Egg")
    // hint: Post_20221104_Paper_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var names = Names.byNames(count1, name1).and(count2, name2).get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
