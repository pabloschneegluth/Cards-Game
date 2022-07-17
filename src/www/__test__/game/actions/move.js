import { click } from "./click";

export function move(fromElement, toElement) {
  click(fromElement);
  click(toElement);
}
