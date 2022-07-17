import { queryHelpers } from "@testing-library/react";
import { verifyContainer } from "../../main/queries";
import { createQueries } from "../../util/createQueries";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (container, ...args) =>
  verifyContainer(container) &&
  queryHelpers.queryAllByAttribute(`data-testid`, container, "idea", ...args);

const getMultipleError = (c, dataKey, dataValue) =>
  `Found multiple elements with the attribute data-testid="idea"`;
const getMissingError = (c, dataKey, dataValue) =>
  `Unable to find an element with the attribute data-testid="idea"`;

export const { queryIdea, queryAllIdea, getAllIdea, getIdea } = createQueries(
  "Idea",
  queryAll,
  getMultipleError,
  getMissingError,
);
