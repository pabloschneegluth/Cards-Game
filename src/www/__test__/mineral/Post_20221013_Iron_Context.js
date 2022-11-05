import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getIdeaDigestByName } from "../idea/queries";
import { queryAllStackDigestByCardNames } from "../stack/queries";
import { Names } from "../util/Names";


export class Post_20221013_Iron_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSCardsAndNSCards() {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();


  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard() {
    // text:  * Given that the odds are that we will get a "Iron" from the "Woods Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Iron", "Woods Stroll Idea")
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20220723_Ideas_Context.endTheCurrentMoon

    await waitForEndMoon();
  }
  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {

    await waitForReloadGame();
  }
  
  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Woods Stroll Idea" may create a "Iron" card.
    // code: await this.theSMayCreateASCard("Woods Stroll Idea", "Iron")
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.theSMayCreateASCard


    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
    }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, count1, name1, count2, name2, count3, name3) {
    // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Iron" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Iron")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSAndNSCards


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
