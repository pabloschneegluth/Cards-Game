import { createQueries } from "../../util/createQueries";
import { digestCardElement } from "./digestCardElement";
import { queryAllCardByName } from "./queryAllCardByName";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (container, cardName, ...args) =>
  queryAllCardByName(container, cardName, ...args).map(digestCardElement);

const getMultipleError = (c, cardName) =>
  `Found multiple elements with the attribute data-cardname="${cardName}"`;
const getMissingError = (c, cardName) =>
  `Unable to find an element with the attribute data-cardname="${cardName}"`;

export const {
  queryCardDigestByName,
  queryAllCardDigestByName,
  getCardDigestByName,
  getAllCardDigestByName,
} = createQueries(
  "CardDigestByName",
  queryAll,
  getMultipleError,
  getMissingError,
);
