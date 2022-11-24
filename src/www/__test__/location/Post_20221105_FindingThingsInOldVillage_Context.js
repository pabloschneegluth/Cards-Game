import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getIdeaDigestByName } from "../idea/queries";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";

export class Post_20221105_FindingThingsInOldVillage_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSNSAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 1 "Old Village", 1 "Old Village Stroll Idea" and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSNSAndNSCards(1, "Old Village", 1, "Old Village Stroll Idea", 1, "Villager")
    // hint: Post_20221105_Flint_Context.givenANewGameWithAStackOfNSNSAndNSCards

    await waitForReloadGame();

  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Old Village Stroll Idea" may create a "Berry" card.
    // code: await this.theSMayCreateASCard("Old Village Stroll Idea", "Berry")
    // hint: Post_20221114_Chicken_Context.theSMayCreateASCard

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Berry" from the "Old Village" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Berry", "Old Village")
    // hint: Post_20221114_Chicken_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_Chicken_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNStacksOfNSNSNSAndNSCards(
    expected,
    count1,
    name1,
    count2,
    name2,
    count3,
    name3,
    count4,
    name4,
  ) {
    // text:  * There should be 1 stacks of 1 "Old Village", 1 "Old Village Stroll Idea", 1 "Villager" and 1 "Berry" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Old Village", 1, "Old Village Stroll Idea", 1, "Villager", 1, "Berry")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSNSAndNSCards

    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .and(count4, name4)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
