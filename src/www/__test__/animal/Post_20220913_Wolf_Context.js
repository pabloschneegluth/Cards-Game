import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20220913_Wolf_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSCardsAndNSCard(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" card.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Woods Stroll Idea", 1, "Villager")
    // hint: Post_20221013_Wood_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();

  }

  async theSMayCreateASCard(s1, s2) {
    // text:  * The "Woods Stroll Idea" may create a "Wolf" card.
    // code: await this.theSMayCreateASCard("Woods Stroll Idea", "Wolf")
    // hint: Post_20221013_Wood_Context.theSMayCreateASCard

  }

  async givenThatTheOddsAreThatWeWillFindASCardFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will find a "Wolf" card from the "Woods Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillFindASCardFromTheSCard("Wolf", "Woods Stroll Idea")

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221013_Wood_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSCardsAndNSCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3
  ) {
    // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" cards and 1 "Wolf" cards
    // code: await this.thereShouldBeNStacksOfNSNSCardsAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Wolf")

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
