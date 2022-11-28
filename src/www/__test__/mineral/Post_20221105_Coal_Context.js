import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getIdeaDigestByName } from "../idea/queries";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";
export class Post_20221105_Coal_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithNStackOfNSNSNSAndNSCards(
    n1,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3,
    n5,
    s4,
  ) {
    // text:  * Given a new game with 1 stack of 1 "Mine Stroll Idea", 1 "Mine", 1 "Villager" and 1 "Pickaxe" cards
    // code: await this.givenANewGameWithNStackOfNSNSNSAndNSCards(1, 1, "Mine Stroll Idea", 1, "Mine", 1, "Villager", 1, "Pickaxe")
    // hint: Post_20221027_Sword_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

    await waitForReloadGame();
  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Mine Stroll Idea" may create a "Stone" card.
    // code: await this.theSMayCreateASCard("Mine Stroll Idea", "Stone")
    // hint: Post_20221106_Lake_Context.theSMayCreateASCard
    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Coal" card from the "Mine Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Coal", "Mine Stroll Idea")
    // hint: Post_20221106_Lake_Context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221027_Sword_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSNSNSAndNSCards(
    expected,
    count1,
    name1,
    count2,
    name2,
    count3,
    name3,
    count4,
    name4,
    count5,
    name5,
  ) {
    // text:  * There should be 1 stacks of 1 "Mine Stroll Idea", 1 "Mine", 1 "Villager", 1 "Pickaxe" and 1 "Coal" cards
    // code: await this.thereShouldBeNStacksOfNSNSNSNSAndNSCards(1, 1, "Mine Stroll Idea", 1, "Mine", 1, "Villager", 1, "Pickaxe", 1, "Coal")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSNSAndNSCards
    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .and(count4, name4)
      .and(count5, name5)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
