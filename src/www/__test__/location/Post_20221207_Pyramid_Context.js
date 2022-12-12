import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {getIdeaDigestByName} from "../idea/queries";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221207_Pyramid_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSNSAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 1 "Dessert Stroll Idea", 1 "Dessert" and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSNSAndNSCards(1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager")
    // hint: Post_20221105_Flint_Context.givenANewGameWithAStackOfNSNSAndNSCards

    await waitForReloadGame();

  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Dessert Stroll Idea" idea at level 4 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Dessert Stroll Idea", 4, 0)
    // hint: Post_20221122_Arrow_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();

  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Dessert Stroll Idea" may create a "Pyramid" card.
    // code: await this.theSMayCreateASCard("Dessert Stroll Idea", "Pyramid")
    // hint: Post_20221106_Lake_Context.theSMayCreateASCard

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Pyramid" card from the "Dessert Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Pyramid", "Dessert Stroll Idea")
    // hint: Post_20221106_Lake_Context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221205_Arakh_Context.endTheCurrentMoon

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
    // text:  * There should be 1 stacks of 1 "Dessert Stroll Idea", 1 "Dessert", 1 "Villager" and 1 "Pyramid" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Dessert Stroll Idea", 1, "Dessert", 1, "Villager", 1, "Pyramid")
    // hint: Post_20221129_FindingThingsInDessert_Context.thereShouldBeNStacksOfNSNSNSAndNSCards

    var names = Names.byNames(n2, s1)
      .and(n3, s2)
      .and(n4, s3)
      .and(n5,s4)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
