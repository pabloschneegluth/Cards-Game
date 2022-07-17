// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom
import "@testing-library/jest-dom";
import { getCodeFrame } from "./www/__test__/util/getCodeFrame";

jest.mock(
  "react-markdown",
  () =>
    ({ children }) =>
      children,
);
jest.mock("remark-gfm", () => {});
jest.mock("unist-util-visit", () => {});

const realTest = global.test;
global.test = function (name, fn) {
  realTest(name, async (...args) => {
    try {
      await fn(...args);
    } catch (e) {
      const frame = e.stack.split("\n").find((s) => s.includes("_Test.spec."));

      e.stack += "\n \n" + getCodeFrame(frame);

      throw e;
    }
  });
};
Object.assign(global.test, realTest);
