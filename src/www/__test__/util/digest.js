import highlight from "@babel/highlight";
import { prettyDOM } from "@testing-library/react";
import prettier from "prettier";
import { getCodeFrame } from "./getCodeFrame";

export function createDigestFunction(typeName, digestElement) {
  const digestFunction = (element) => {
    const digest = {
      typeName,
      getElement: () => element,
      debugElement: () => debugElement(element),
      debug: () => debugDigest(digest),
      ...digestElement(element),
    };
    return digest;
  };

  Object.defineProperty(digestFunction, "name", {
    value: `digest${typeName[0].toUpperCase()}${typeName.slice(1)}`,
  });
  return digestFunction;
}

function debugElement(element) {
  const output = [indent("    ", prettyDOM(element)), "", showCodeFrame(3)];

  console.log(output.join("\n"));
}

function indent(indent, output) {
  const lines = output.split("\n").map((line) => `${indent}${line}`);
  return lines.join("\n");
}

function showCodeFrame(discardFrames) {
  const stack = new Error().stack;
  const [frame] = stack.split("\n").slice(discardFrames + 1);

  return getCodeFrame(frame);
}

function debugDigest(digest) {
  const json = JSON.stringify(digest);
  const methods = Object.keys(digest).filter(
    (k) => typeof digest[k] === "function",
  );

  let output = [
    prettyJs(`const ${digest.typeName}Digest = ${json};`).slice(0, -3),
    ...methods.map((m) => {
      switch (m) {
        case "getElement":
          return `  ${m}() { return /* the DOM element of this digest */ },\n`;
        case "debugElement":
          return `  ${m}() { /* shows the DOM html of this element */ },\n`;
        case "debug":
          return `  ${m}() { /* shows this message */ },\n`;
        default:
          if (digest[m].length === 0) return `  ${m}() { ... },\n`;
          return `  ${m}(...) { ... },\n`;
      }
    }),
    "};",
  ];

  console.log(
    highlight(indent("     ", output.join(""))),
    "\n\n",
    showCodeFrame(3),
  );
}

function prettyJs(code) {
  return prettier.format(code, { parser: "babel" });
}
