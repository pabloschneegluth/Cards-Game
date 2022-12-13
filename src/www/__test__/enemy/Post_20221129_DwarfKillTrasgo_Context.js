import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221129_DwarfKillTrasgo_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Dwarf" cards.
    // code: await this.givenThereAreNSCards(1, "Dwarf")
    // hint: Post_20221207_HunterKillGrizzly_Context.givenThereAreNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenThereAreNSCards(n1, s1) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221207_HunterKillGrizzly_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 0 "Trasgo" cards.
    // code: await this.thereShouldBeNSCards(0, "Trasgo")
    // hint: Post_20221207_HunterKillGrizzly_Context.thereShouldBeNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNSCards(expected, s1) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
