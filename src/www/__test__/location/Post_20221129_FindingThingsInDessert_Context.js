import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {getIdeaDigestByName} from "../idea/queries";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221129_FindingThingsInDessert_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSNSAndNS(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 1 "Dessert Stroll Idea", 1 "Dessert" and 1 "Villager".
    // code: await this.givenANewGameWithAStackOfNSNSAndNS(1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager")
    // hint: Post_20221128_MountainKit_Context.givenANewGameWithAStackOfNSNSNSAndNS

    await waitForReloadGame();
  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Dessert Stroll Idea" may create a "Scorpion" card.
    // code: await this.theSMayCreateASCard("Dessert Stroll Idea", "Scorpion")
    // hint: Post_20221105_Coal_Context.theSMayCreateASCard

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Scorpion" from the "Dessert Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Scorpion", "Dessert Stroll Idea")
    // hint: Post_20221024_Diamond_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221127_Rod_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSNSAndNSCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3,
    n5,
    s4
  ) {
    // text:  * There should be 1 stacks of 1 "Dessert Stroll Idea", 1 "Dessert", 1 "Villager" and 1 "Scorpion" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager", 1, "Scorpion")
    // hint: Post_20221124_FindingThingsInSawmill_Context.thereShouldBeNStacksOfNSNSNSAndNSCards

    var names = Names.byNames(n2, s1)
      .and(n3, s2)
      .and(n4, s3)
      .and(n5, s4)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
