import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { queryAllCardByName } from "../card/queries";

export class Post_20221127_MilitiaKillZombie_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Militia" cards.
    // code: await this.givenThereAreNSCards(1, "Militia")
    // hint: Post_20221114_WolfKillVillager_Context.givenThereAreNSCards

    await waitForReloadGame();


  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNSCards(count, cardName) {
    // text:  * There should be 0 "Creeper" cards.
    // code: await this.thereShouldBeNSCards(0, "Creeper")
    // hint: Post_20221114_WolfKillVillager_Context.thereShouldBeNSCards

    var actual = queryAllCardByName(mainView, cardName);
    expect(actual).toHaveLength(count);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
