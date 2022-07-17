import { createQueries } from "../../util/createQueries";
import { queryAllStackDigest } from "./queryAllStackDigest";

const queryAll = (container, names) =>
  queryAllStackDigest(container).filter(
    (stack) => stack.cards.map((c) => c.name).join("#") === names.join("#"),
  );
const getMultipleError = (c, names) =>
  `Found multiple elements with the attribute data-testid="stack" and card names="${names.join(
    ",",
  )}"`;
const getMissingError = (c, names) =>
  `Unable to find an element with the attribute data-testid="stack" and card names="${names.join(
    ",",
  )}"`;

export const {
  queryStackDigestByCardNames,
  getAllStackDigestByCardNames,
  queryAllStackDigestByCardNames,
  getStackDigestByCardNames,
} = createQueries(
  "StackDigestByCardNames",
  queryAll,
  getMultipleError,
  getMissingError,
);
