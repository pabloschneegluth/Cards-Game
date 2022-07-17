import { screen } from "@testing-library/react";
import { mainView } from "../../main";
import {
  getAllCardDigestByName,
  queryAllCardByName,
  queryCardByName,
} from "../../card/queries";
import { waitForEnterTheGame } from "../../game/actions";
import { waitForEndMoon } from "../../moon/actions";

export class Post_20220719_VillagersEatFood_Context {
  async beforeTest() {}

  async givenWeHaveEnteredIntoANewGame() {
    // example:  * Given we have entered into a new game.
    await waitForEnterTheGame();
  }

  async theSCardShouldHaveNInSTag(cardName, count, tagName) {
    // example:  * The "berry" card has 1 in "food" tag.
    // the = "berry"
    // has = 1
    // arg2 = "food"

    const [card] = getAllCardDigestByName(mainView, cardName);
    expect(card.tags[tagName]).toEqual(count);
  }

  async endTheCurrentMoon() {
    // example:  * End the current moon.
    await waitForEndMoon();
  }

  async thereShouldBeNoSCard(cardName) {
    // example:  * There is no "berry" card.
    // no = "berry"

    const card = queryCardByName(mainView, cardName);
    expect(card).not.toBeInTheDocument();
  }

  async thereShouldBeNSCards(count, cardName) {
    // example:  * There is 1 "villager" card.
    // is = 1
    // n = "villager"

    const cards = queryAllCardByName(mainView, cardName);
    expect(cards).toHaveLength(count);
  }

  async afterTest() {}
}
