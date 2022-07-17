import { getAllCardByName } from "../card/queries";
import { mainView } from "../main";
import { waitForEnterTheGame } from "../game/actions";

export class Post_20220717_BushesVillagersAndBerries_Context {
  async beforeTest() {}

  async enterTheGame() {
    // example:  * Enter in the game.
    await waitForEnterTheGame();
  }

  async thereShouldBeNSCards(expectedCount, cardName) {
    // example:  * There should be 1 "villager" card.
    // expected = 1
    // arg1 = "villager"

    const cards = getAllCardByName(mainView, cardName);
    expect(cards).toHaveLength(expectedCount);
  }

  async afterTest() {}
}
