import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221020_BuildIdea_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();


  }

  async givenANewGameWithAStackOfNSCardsNSCardAndNSCard( n1,  s1,  n2,  s2,  n3,  s3) {
    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Harvest Idea" idea at level 2 and 9 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Harvest Idea", 2, 9)
    // hint: Post_20221013_Wood_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();


  }

  async thereShouldBeNoSIdea(expected) {
    // text:  * There should be no "Build Idea" idea.
    // code: await this.thereShouldBeNoSIdea("Build Idea")
    // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeNoSIdea

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221013_Wood_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async theSShouldHaveLevelNAndNXp(s1, n1, n2) {
    // text:  * The "Harvest Idea" should have level 3 and 0 XP.
    // code: await this.theSShouldHaveLevelNAndNXp("Harvest Idea", 3, 0)
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.theSShouldHaveLevelNAndNXp

  }

  async thereShouldBeTheSIdea(expected) {
    // text:  * There should be the "Build Idea" idea.
    // code: await this.thereShouldBeTheSIdea("Build Idea")
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.thereShouldBeTheSIdea

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
