import { mainView } from "../../main";
import { move } from "../../main/actions";
import { waitForLoading } from "../../loading/actions";
import { queryAllStackDigest } from "../queries";

export async function waitForMoveCardToItsOwnStack(card) {
  var stacks = queryAllStackDigest(mainView);
  var emptyStack = stacks.find((stack) => stack.cards.length === 0);

  move(card, emptyStack.getElement());

  await waitForLoading();
}
