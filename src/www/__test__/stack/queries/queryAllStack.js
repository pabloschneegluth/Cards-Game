import { queryHelpers } from "@testing-library/react";
import { createQueries } from "../../util/createQueries";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (container, ...args) =>
  queryHelpers.queryAllByAttribute(`data-testid`, container, "stack", ...args);

export const getMultipleStackError = (c, dataKey, dataValue) =>
  `Found multiple elements with the attribute data-testid="stack"`;
export const getMissingStackError = (c, dataKey, dataValue) =>
  `Unable to find an element with the attribute data-testid="stack"`;

export const { queryStack, getAllStack, queryAllStack, getStack } =
  createQueries("Stack", queryAll, getMultipleStackError, getMissingStackError);
