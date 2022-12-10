import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {queryAllCardByName} from "../card/queries";

export class Post_20221207_HunterKillGrizzly_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Hunter" cards.
    // code: await this.givenThereAreNSCards(1, "Hunter")
    // hint: Post_20221120_ChickenLaysEgg_Context.givenThereAreNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221120_ChickenLaysEgg_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 0 "Grizzly" cards.
    // code: await this.thereShouldBeNSCards(0, "Grizzly")
    // hint: Post_20221117_FriendlyWolfKillWolf_Context.thereShouldBeNSCards

    var actual = queryAllCardByName(mainView, s1);
    expect(actual).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
