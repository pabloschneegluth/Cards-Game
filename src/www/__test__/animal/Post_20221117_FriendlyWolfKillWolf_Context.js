import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {queryAllCardByName} from "../card/queries";

export class Post_20221117_FriendlyWolfKillWolf_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Friendly Wolf" cards and 1 "Wolf" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Friendly Wolf", 1, "Wolf")
    // hint: Post_20221114_Chicken_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 0 "Friendly Wolf" cards.
    // code: await this.thereShouldBeNSCards(0, "Friendly Wolf")
    // hint: Post_20221114_WolfKillVillager_Context.thereShouldBeNSCards

    var actual = queryAllCardByName(mainView, s1);
    expect(actual).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
