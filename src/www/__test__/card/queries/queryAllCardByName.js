import { queryHelpers } from "@testing-library/react";
import { createQueries } from "../../util/createQueries";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (container, cardName, ...args) =>
  queryHelpers.queryAllByAttribute(
    `data-cardname`,
    container,
    cardName,
    ...args,
  );

const getMultipleError = (c, cardName) =>
  `Found multiple elements with the attribute data-cardname="${cardName}"`;
const getMissingError = (c, cardName) =>
  `Unable to find an element with the attribute data-cardname="${cardName}"`;

export const {
  queryCardByName,
  queryAllCardByName,
  getCardByName,
  getAllCardByName,
} = createQueries("CardByName", queryAll, getMultipleError, getMissingError);
