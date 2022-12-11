import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {Names} from "../util/Names";
import {queryAllCardDigestByName} from "../card/queries";

export class Post_20221207_DothrakiKillScorpion_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Dothraki" cards.
    // code: await this.givenThereAreNSCards(1, "Dothraki")
    // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNSCards

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221127_Rod_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 0 "Scorpion" cards.
    // code: await this.thereShouldBeNSCards(0, "Scorpion")
    // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeNSCards

    var names = Names.byNames(expected, s1).get();

    var stacks = queryAllCardDigestByName(mainView, names);
    expect(stacks).toHaveLength(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
