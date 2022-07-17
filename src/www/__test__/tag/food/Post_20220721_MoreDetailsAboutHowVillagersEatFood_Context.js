import { getByTestId, screen } from "@testing-library/react";
import {
  getAllCardDigestByName,
  queryAllCardByName,
  queryAllCardDigest,
} from "../../card/queries";
import { mainView } from "../../main";
import { waitForReloadGame } from "../../game/actions";
import { waitForEndMoon } from "../../moon/actions";

export class Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context {
  async beforeTest() {}

  async givenThereAreNSAndNSCards() {
    await waitForReloadGame();
  }

  async theSCardShouldHaveNInSTag(cardName, count, tagName) {
    // example:  * The "villager" card should have 1 in "eats" tag.
    // the = "villager"
    // have = 1
    // arg2 = "eats"

    var [card] = getAllCardDigestByName(mainView, cardName);
    expect(card.tags[tagName]).toEqual(count);
  }

  async theSumOfAllSTagsValueShouldBeN(tagName, expected) {
    // example:  * The sum of all "eats" tags value should be 12.
    // all = "eats"
    // expected = 12

    var cards = queryAllCardDigest(mainView);
    var actual = cards.reduce((acc, c) => acc + c.getTag(tagName), 0);
    expect(actual).toEqual(expected);
  }

  async endTheCurrentMoon() {
    // example:  * End the current moon.
    await waitForEndMoon();
  }

  async thereShouldBeNSCards(expected, cardName) {
    // example:  * There should be 2 "berry" cards.
    // expected = 2
    // arg1 = "berry"

    var actual = queryAllCardByName(mainView, cardName);
    expect(actual).toHaveLength(expected);
  }

  async afterTest() {}
}
