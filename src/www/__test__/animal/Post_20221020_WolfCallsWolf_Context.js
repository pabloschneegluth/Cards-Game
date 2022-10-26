import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { queryAllCardByName } from "../card/queries";

export class Post_20221020_WolfCallsWolf_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Wolf" cards.
    // code: await this.givenThereAreNSCards(1, "Wolf")
    // hint: Post_20220913_KillingWolf_Context.givenThereAreNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221013_Wood_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNSCards(count, cardName) {
    // text:  * There should be 2 "Wolf" cards.
    // code: await this.thereShouldBeNSCards(2, "Wolf")
    // hint: Post_20220913_KillingWolf_Context.thereShouldBeNSCards

    var actual = queryAllCardByName(mainView, cardName);
    expect(actual).toHaveLength(count);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
