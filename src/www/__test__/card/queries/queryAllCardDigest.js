import { createQueries } from "../../util/createQueries";
import { digestCardElement } from "./digestCardElement";
import { queryAllCard } from "./queryAllCard";

const queryAll = (container, ...args) =>
  queryAllCard(container, ...args).map(digestCardElement);

const getMultipleError = (c, dataKey, dataValue) =>
  `Found multiple elements with the attribute data-testid="card"`;
const getMissingError = (c, dataKey, dataValue) =>
  `Unable to find an element with the attribute data-testid="card"`;

export const {
  queryCardDigest,
  getAllCardDigest,
  queryAllCardDigest,
  getCardDigest,
} = createQueries("CardDigest", queryAll, getMultipleError, getMissingError);
