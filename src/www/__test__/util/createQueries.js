import { buildQueries } from "@testing-library/react";
import chalk from "chalk";

export function createQueries(
  queryName,
  queryAll,
  getMultipleError,
  getMissingError,
) {
  const [query, getAll, get] = buildQueries(
    queryAll,
    getMultipleError,
    getMissingError,
  );

  const result = {};
  buildQuery(result, `query${queryName}`, query);
  buildQuery(result, `queryAll${queryName}`, queryAll);
  buildQuery(result, `get${queryName}`, get);
  buildQuery(result, `getAll${queryName}`, getAll);

  return result;
}

function buildQuery(result, methodName, query) {
  function fn(container, ...rest) {
    verifyContainer(methodName, container);
    return query(container, ...rest);
  }

  Object.defineProperty(fn, "name", { value: methodName, configurable: true });
  result[methodName] = fn;
}

function verifyContainer(methodName, container) {
  if (typeof container?.querySelectorAll === "function") return true;

  throw new TypeError(
    `The first argument of queries must be a valid DOM container. ` +
      `In this case, it seems that you have called to a ${methodName} ` +
      `method with a first argument that is not a container. ` +
      `If you do not know which container use, you may want to use mainView, example:\n\n` +
      chalk.red.strikethrough(`- ${methodName}(…);\n`) +
      chalk.green(`- ${methodName}(${chalk.bold("mainView")}, …);\n`),
  );
}
