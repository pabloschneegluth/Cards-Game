import { queryHelpers } from "@testing-library/react";
import { createQueries } from "../../util/createQueries";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (container, key, ...args) =>
  queryHelpers.queryAllByAttribute(`data-${key}`, container, ...args);

const getMultipleError = (c, dataKey, dataValue) =>
  `Found multiple elements with the attribute data-${dataKey}="${dataValue}"`;
const getMissingError = (c, dataKey, dataValue) =>
  `Unable to find an element with the attribute data-${dataKey}="${dataValue}"`;

export const {
  queryByData,
  queryAllByData,
  getByData,
  getAllByData,
  findAllByData,
  findByData,
} = createQueries(queryAll, getMultipleError, getMissingError);
