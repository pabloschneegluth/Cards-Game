import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {getIdeaDigestByName} from "../idea/queries";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221128_Grizzly_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSNSNSCardsAndNS(
    n1,
    s1,
    n2,
    s2,
    n3,
    s3,
    n4,
    s4
  ) {
    // text:  * Given a new game with a stack of 1 "Snowy Mountain Stroll Idea", 1 "Snowy Mountain", 1 "Villager" cards and 1 "Mountain Kit".
    // code: await this.givenANewGameWithAStackOfNSNSNSCardsAndNS(1, "Snowy Mountain Stroll Idea", 1, "Snowy Mountain", 1, "Villager", 1, "Mountain Kit")

    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Snowy Mountain Stroll Idea" idea at level 1 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Snowy Mountain Stroll Idea", 1, 0)
    // hint: Post_20221122_Arrow_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();
  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Snowy Mountain Stroll Idea" may create a "Grizzly" card.
    // code: await this.theSMayCreateASCard("Snowy Mountain Stroll Idea", "Grizzly")
    // hint: Post_20221105_Coal_Context.theSMayCreateASCard

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221122_Arrow_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSNSNSCardsAndNSCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3,
    n5,
    s4,
    n6,
    s5
  ) {
    // text:  * There should be 1 stacks of 1 "Snowy Mountain Stroll Idea", 1 "Snowy Mountain", 1 "Villager", 1 "Mountain Kit" cards and 1 "Grizzly" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSNSCardsAndNSCards(1, 1, "Snowy Mountain Stroll Idea", 1, "Snowy Mountain", 1, "Villager", 1, "Mountain Kit", 1, "Grizzly")

    var names = Names.byNames(n2, s1)
      .and(n3, s2)
      .and(n4, s3)
      .and(n5,s4)
      .and(n6,s5)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
