const path = require("node:path");
const { outputFile } = require("fs-extra");
const { join } = require("./join");
const { prettyJs } = require("./prettyJs");
const { findClosestMethod } = require("./posts");

async function writeJsContextFile(post) {
  const contextPath = path.join(
    "src",
    "www",
    "__test__",
    ...post.subPath,
    post.contextName + ".js",
  );

  const contextContent = makeContextContent(post);

  try {
    await outputFile(contextPath, prettyJs(contextContent), { flag: "wx" });
    return true;
  } catch (e) {
    if (e.code !== "EEXIST") console.error(e);
  }

  return false;
}

function makeContextContent(post) {
  let contextContent = "";

  contextContent += makeContextHeader(post);
  contextContent += makeContextBody(post);
  contextContent += makeContextFooter();

  return contextContent;
}

function makeContextHeader(post) {
  return join(
    `import { getByTestId, screen } from "@testing-library/react";`,
    `import { mainView } from "${post.parent}/main";`,
    `import { waitForEnterTheGame, waitForReloadGame } from "${post.parent}/game/actions";`,
    `import { waitForEndMoon } from "${post.parent}/moon/actions";`,
    ``,
    `export class ${post.contextName} {`,
    `  async beforeTest() {`,
    `    // Do your setup here`,
    `    await waitForEnterTheGame();`,
    `    `,
    `    throw new Error("Please, review the implementation of beforeTest() and remove this exception when it is correct.");`,
    `  }`,
  );
}

function makeContextBody(post) {
  return join(
    ...post.contextMethods.flatMap((m) => makeContextMethod(post, m)),
  );
}

function makeContextMethod(post, method) {
  const { name, arguments: args, text } = method;
  const formalArguments = args.map(({ name }) => `${name}`);
  const methodSignature = `${name}(${formalArguments.join(", ")})`;
  const closest = findClosestMethod(post, method);
  const lowerName = name.toLowerCase();

  return [
    ``,
    `  async ${methodSignature} {`,
    `    // text: ${text}`,
    `    // code: await this.${name}(${args.map((a) => a.value).join(", ")})`,
    makeContextMethodHint(closest),
    ``,
    makeContextMethodGiven(name),
    makeContextMethodEndMoon(lowerName),
    makeContextMethodExpect(args),
    ``,
    `    throw new Error("The method ${methodSignature} is not implemented yet.");`,
    `  }`,
  ];
}

function makeContextMethodGiven(name) {
  return name.startsWith("given") && ["await waitForReloadGame();"];
}

function makeContextMethodEndMoon(lowerName) {
  return (
    lowerName.includes("end") &&
    lowerName.includes("moon") && ["await waitForEndMoon();"]
  );
}

function makeContextMethodExpect(args) {
  return (
    args.some(({ name }) => name === "expected") && [
      `    var actual = expected; // FIXME`,
      `    expect(actual).toEqual(expected);`,
      ``,
    ]
  );
}

function makeContextMethodHint(closest) {
  return (
    closest?.distance < 4 &&
    `    // hint: ${closest.post.contextName}.${closest.name}`
  );
}

function makeContextFooter() {
  return join(
    ``,
    `  async afterTest() {`,
    `    // Do your teardown here, if necessary`,
    `  }`,
    `}`,
  );
}

exports.writeJsContextFile = writeJsContextFile;
