import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221020_FriendlyWolf_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenAStackOfNSNSAndNS(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a stack of 1 "Wolf", 1 "Villager" and 1 "Bone".
    // code: await this.givenAStackOfNSNSAndNS(1, "Wolf", 1, "Villager", 1, "Bone")

    await waitForReloadGame();

  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Friendly Wolf" from the "Wolf" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Friendly Wolf", "Wolf")
    // hint: Post_20221013_Iron_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221013_Iron_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNSCard(expected, s1) {
    // text:  * There should be 0 "Wolf" card.
    // code: await this.thereShouldBeNSCard(0, "Wolf")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async thereShouldBeNStackOfNSAndNS(expected, n2, s1, n3, s2) {
    // text:  * There should be 1 stack of 1 "Villager" and 1 "Friendly Wolf"
    // code: await this.thereShouldBeNStackOfNSAndNS(1, 1, "Villager", 1, "Friendly Wolf")

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
