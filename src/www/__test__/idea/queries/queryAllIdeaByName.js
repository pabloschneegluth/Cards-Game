import { queryHelpers } from "@testing-library/react";
import { createQueries } from "../../util/createQueries";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (container, ideaName, ...args) =>
  queryHelpers.queryAllByAttribute(
    `data-ideaname`,
    container,
    ideaName,
    ...args,
  );

const getMultipleError = (c, ideaName) =>
  `Found multiple elements with the attribute data-ideaname="${ideaName}"`;
const getMissingError = (c, ideaName) =>
  `Unable to find an element with the attribute data-ideaname="${ideaName}"`;

export const {
  queryIdeaByName,
  queryAllIdeaByName,
  getIdeaByName,
  getAllIdeaByName,
} = createQueries("IdeaByName", queryAll, getMultipleError, getMissingError);
