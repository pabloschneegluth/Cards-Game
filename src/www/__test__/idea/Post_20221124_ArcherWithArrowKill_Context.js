import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getIdeaDigestByName } from "../idea/queries";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";

export class Post_20221124_ArcherWithArrowKill_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSNSAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 1 "Archer", 1 "Arrow" and 1 "Wolf" cards.
    // code: await this.givenANewGameWithAStackOfNSNSAndNSCards(1, "Archer", 1, "Arrow", 1, "Wolf")
    // hint: Post_20221105_Flint_Context.givenANewGameWithAStackOfNSNSAndNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeAStackWithNSNSAndNSCards(
    count1,
    name1,
    count2,
    name2,
    count3,
    name3,
  ) {
    // text:  * There should be a stack with 1 "Archer", 0 "Arrow" and 0 "Wolf" cards.
    // code: await this.thereShouldBeAStackWithNSNSAndNSCards(1, "Archer", 0, "Arrow", 0, "Wolf")
    // hint: Post_20221119_HowToKillAnimals_Context.thereShouldBeAStackWithNSAndNSCards
    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)

      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(1);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
