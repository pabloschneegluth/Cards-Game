import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {
  queryAllCardByName,
  getAllCardDigestByName,
  queryAllCardDigest,
} from "../card/queries";

export class Post_20221106_Fish_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Fish" cards.
    // code: await this.givenThereAreNSCards(1, "Fish")
    // hint: Post_20221020_WolfCallsWolf_Context.givenThereAreNSCards

    await waitForReloadGame();
  }

  async theSumOfAllSTagsValueShouldBeN(tagName, expected) {
    // text:  * The sum of all "Eats" tags value should be 3.
    // code: await this.theSumOfAllSTagsValueShouldBeN("Eats", 3)
    // hint: Post_20221020_Pear_Context.theSumOfAllSTagsValueShouldBeN

    var cards = queryAllCardDigest(mainView);
    var actual = cards.reduce((acc, c) => acc + c.getTag(tagName), 0);
    expect(actual).toEqual(expected);
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNSCards(expected, cardName) {
    // text:  * There should be 3 "Villager" cards.
    // code: await this.thereShouldBeNSCards(3, "Villager")
    // hint: Post_20221020_WolfCallsWolf_Context.thereShouldBeNSCards

    var actual = queryAllCardByName(mainView, cardName);
    expect(actual).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
