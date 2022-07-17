import { queryHelpers } from "@testing-library/react";
import { createQueries } from "../../util/createQueries";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (container, position, ...args) =>
  queryHelpers.queryAllByAttribute(
    `data-stackposition`,
    container,
    position,
    ...args,
  );

const getMultipleError = (c, position, dataKey, dataValue) =>
  `Found multiple elements with the attribute data-stackposition="${position}"`;
const getMissingError = (c, position, dataKey, dataValue) =>
  `Unable to find an element with the attribute data-stackposition="${position}"`;

export const {
  queryStackByPosition,
  getAllStackByPosition,
  queryAllStackByPosition,
  getStackByPosition,
} = createQueries(
  "StackByPosition",
  queryAll,
  getMultipleError,
  getMissingError,
);
