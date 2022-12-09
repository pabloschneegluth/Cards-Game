import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {getIdeaDigestByName} from "../idea/queries";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221128_FindingThingsInSnowyMountain_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSNSNSCardAndNS(
    n1,
    s1,
    n2,
    s2,
    n3,
    s3,
    n4,
    s4
  ) {
    // text:  * Given a new game with a stack of 1 "Snowy Mountain Stroll Idea", 1 "Snowy Mountain", 1 "Villager" card and 1 "Mountain Kit".
    // code: await this.givenANewGameWithAStackOfNSNSNSCardAndNS(1, "Snowy Mountain Stroll Idea", 1, "Snowy Mountain", 1, "Villager", 1, "Mountain Kit")
    // hint: Post_20221128_Grizzly_Context.givenANewGameWithAStackOfNSNSNSCardsAndNS

    await waitForReloadGame();

  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Snowy Mountain Stroll Idea" may create a "Grizzly" card.
    // code: await this.theSMayCreateASCard("Snowy Mountain Stroll Idea", "Grizzly")
    // hint: Post_20221031_String_Context.theSMayCreateASCard

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Grizzly" from the "Snowy Mountain" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Grizzly", "Snowy Mountain")
    // hint: Post_20221020_Bone_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

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
    // text:  * There should be 1 stacks of 1 "Snowy Mountain Stroll Idea", 1 "Snowy Mountain", 1 "Villager" and 1 "Grizzly" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Snowy Mountain Stroll Idea", 1, "Snowy Mountain", 1, "Villager", 1, "Grizzly")
    // hint: Post_20221129_Mummy_Context.thereShouldBeNStacksOfNSNSNSAndNSCards

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
