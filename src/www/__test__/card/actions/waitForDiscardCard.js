import { getByTestId } from "@testing-library/react";
import { mainView } from "../../main";
import { move } from "../../main/actions";
import { waitForLoading } from "../../loading/actions";

export async function waitForDiscardCard(card) {
  var discard = getByTestId(mainView, "discard");
  move(card, discard);
  await waitForLoading();
}
