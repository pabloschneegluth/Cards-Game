import { createQueries } from "../../util/createQueries";
import { digestStackElement } from "./digestStackElement";
import {
  getMissingStackError,
  getMultipleStackError,
  queryAllStack,
} from "./queryAllStack";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (...args) => queryAllStack(...args).map(digestStackElement);

export const {
  queryStackDigest,
  getAllStackDigest,
  queryAllStackDigest,
  getStackDigest,
} = createQueries(
  "StackDigest",
  queryAll,
  getMultipleStackError,
  getMissingStackError,
);
