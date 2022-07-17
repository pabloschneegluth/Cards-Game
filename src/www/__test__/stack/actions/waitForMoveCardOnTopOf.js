import { move } from "../../main/actions";
import { waitForLoading } from "../../loading/actions";

export async function waitForMoveCardOnTopOf(
  topCardElement,
  bottomCardElement,
) {
  move(topCardElement, bottomCardElement);
  await waitForLoading();
}
