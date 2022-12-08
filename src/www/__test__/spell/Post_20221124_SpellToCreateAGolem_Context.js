import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221124_SpellToCreateAGolem_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithAStackOfNSNSNSNSNS(
    n1,
    s1,
    n2,
    s2,
    n3,
    s3,
    n4,
    s4,
    n5,
    s5
  ) {
    // text:  * Given a new game with a stack of 1 "Magic Idea", 1 "Wizard", 5 "Bone", 3 "Iron", 1"Gold".
    // code: await this.givenANewGameWithAStackOfNSNSNSNSNS(1, "Magic Idea", 1, "Wizard", 5, "Bone", 3, "Iron", 1, "Gold")

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithAStackOfNSNSNSNSNS(n1, s1, n2, s2, n3, s3, n4, s4, n5, s5) is not implemented yet."
    );
  }

  async theSMayCreateASCard(s1, s2) {
    // text:  * The "Magic Idea" may create a "Golem Spell" card.
    // code: await this.theSMayCreateASCard("Magic Idea", "Golem Spell")
    // hint: Post_20221105_Coal_Context.theSMayCreateASCard

    throw new Error(
      "The method theSMayCreateASCard(s1, s2) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221122_Arrow_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Magic Idea", 1 "Wizard" and 1 "Golem Spell" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Magic Idea", 1, "Wizard", 1, "Golem Spell")
    // hint: Post_20221122_Arrow_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
