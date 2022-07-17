import { createQueries } from "../../util/createQueries";
import { digestIdeaElement } from "./digestIdeaElement";
import { queryAllIdeaByName } from "./queryAllIdeaByName";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (container, ideaName, ...args) =>
  queryAllIdeaByName(container, ideaName, ...args).map(digestIdeaElement);

const getMultipleError = (c, ideaName) =>
  `Found multiple elements with the attribute data-ideaname="${ideaName}"`;
const getMissingError = (c, ideaName) =>
  `Unable to find an element with the attribute data-ideaname="${ideaName}"`;

export const {
  queryIdeaDigestByName,
  queryAllIdeaDigestByName,
  getIdeaDigestByName,
  getAllIdeaDigestByName,
} = createQueries(
  "IdeaDigestByName",
  queryAll,
  getMultipleError,
  getMissingError,
);
