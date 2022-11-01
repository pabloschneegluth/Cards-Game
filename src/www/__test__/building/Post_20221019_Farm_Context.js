import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221019_Farm_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async thereShouldBeNSCard(expected, s1) {
    // text:  * there should be 0 "Farm" card.
    // code: await this.thereShouldBeNSCard(0, "Farm")
    // hint: Post_20221020_FriendlyWolf_Context.thereShouldBeNSCard

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async givenThereIsNStackOfNSAtLevelNAndNXpNSNSNSCards(
    n1,
    n2,
    s1,
    n3,
    n4,
    n5,
    s2,
    n6,
    s3,
    n7,
    s4
  ) {
    // text:  * given there is 1 stack of 1 "Build Idea" at level 2 and 6 XP, 1 "Villager", 1 "Stone", 2 "Wood" cards
    // code: await this.givenThereIsNStackOfNSAtLevelNAndNXpNSNSNSCards(1, 1, "Build Idea", 2, 6, 1, "Villager", 1, "Stone", 2, "Wood")

    await waitForReloadGame();


  }

  async endTheCurrentMoon() {
    // text:  * end the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221024_Pickaxe_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNStackOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * there should be 1 stack of 0 "Stone", 0 "Wood" and 0 "Berry Bush" cards.
    // code: await this.thereShouldBeNStackOfNSNSAndNSCards(1, 0, "Stone", 0, "Wood", 0, "Berry Bush")
    // hint: Post_20221024_Diamond_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async givenANewEmptyGame() {
    // text:  * Given a new empty game
    // code: await this.givenANewEmptyGame()

    await waitForReloadGame();

  }

  async givenThereIsNStackOfNSCardsNSCardsAndNSCardsOrAnotherPlant(
    n1,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3
  ) {
    // text:  * Given there is 1 stack of 1 "Farm" cards, 1 "Villager" cards and 1 "Berry Bush" cards or another plant.
    // code: await this.givenThereIsNStackOfNSCardsNSCardsAndNSCardsOrAnotherPlant(1, 1, "Farm", 1, "Villager", 1, "Berry Bush")

    await waitForReloadGame();

  }

  async thereShouldBeNStackOfNSCardsNSCardsNSAndNSCards(
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
    // text:  * There should be 1 stack of 1 "Farm" cards, 1 "Villager" cards, 1 "Berry Bush" and 5 "Berry" cards
    // code: await this.thereShouldBeNStackOfNSCardsNSCardsNSAndNSCards(1, 1, "Farm", 1, "Villager", 1, "Berry Bush", 5, "Berry")
    // hint: Post_20221013_StoneHouse_Context.thereShouldBeNStacksOfNSCardsNSCardsAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
