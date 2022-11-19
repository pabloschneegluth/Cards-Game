import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getIdeaDigestByName } from "../idea/queries";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";



export class Post_20221106_Lake_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")
    // hint: Post_20221030_Cow_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();

  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Woods Stroll Idea" idea at level 2 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 2, 0)
    // hint: Post_20221030_Cow_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();


  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Woods Stroll Idea" may create a "Lake" card.
    // code: await this.theSMayCreateASCard("Woods Stroll Idea", "Lake")
    // hint: Post_20221030_Cow_Context.theSMayCreateASCard
    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);


  }

  async givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Lake" card from the "Woods Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Lake", "Woods Stroll Idea")
    // hint: Post_20221103_OldVillage_Context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221023_Barracks_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected,
    count1,
    name1,
    count2,
    name2,
    count3,
    name3,) {
    // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Lake" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Lake")
    // hint: Post_20221030_Cow_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}