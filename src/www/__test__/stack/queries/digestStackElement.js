import { queryAllCardDigest } from "../../card/queries";
import { createDigestFunction } from "../../util/digest";

export const digestStackElement = createDigestFunction(
  "stack",
  (stackElement) => {
    const stackDigest = {
      position: +stackElement.dataset.stackposition,
      cards: queryAllCardDigest(stackElement).sort(
        (a, b) => a.zindex - b.zindex,
      ),
    };

    return stackDigest;
  },
);
