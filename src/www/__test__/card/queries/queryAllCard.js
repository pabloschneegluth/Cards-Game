import { queryHelpers } from "@testing-library/react";
import { createQueries } from "../../util/createQueries";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (container, ...args) =>
  queryHelpers.queryAllByAttribute(`data-testid`, container, "card", ...args);

const getMultipleError = (c, dataKey, dataValue) =>
  `Found multiple elements with the attribute data-testid="card"`;
const getMissingError = (c, dataKey, dataValue) =>
  `Unable to find an element with the attribute data-testid="card"`;

export const { queryCard, getAllCard, queryAllCard, getCard } = createQueries(
  "Card",
  queryAll,
  getMultipleError,
  getMissingError,
);
