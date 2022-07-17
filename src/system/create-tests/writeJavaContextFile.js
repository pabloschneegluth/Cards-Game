const path = require("node:path");
const { outputFile } = require("fs-extra");
const { join } = require("./join");
const { findClosestMethod } = require("./posts");

async function writeJavaContextFile(post) {
  const contextPath = path.join(
    "src",
    "test",
    "java",
    "com",
    "drpicox",
    "game",
    ...post.subPath,
    post.contextName + ".java",
  );

  const contextContent = makeContextContent(post);

  try {
    await outputFile(contextPath, contextContent, { flag: "wx" });
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
    `package com.drpicox.game${post.subPackage};`,
    ``,
    `import org.springframework.stereotype.Component;`,
    `import static com.google.common.truth.Truth.assertThat;`,
    `import static com.google.common.truth.Truth8.assertThat;`,
    `import com.drpicox.game.util.FrontendSimulator;`,
    `import com.drpicox.game.game.GivenGameService;`,
    `import com.drpicox.game.card.GivenCardService;`,
    `import com.drpicox.game.game.api.GameDTO;`,
    ``,
    `@Component`,
    `public class ${post.contextName} {`,
    ``,
    `    private final FrontendSimulator frontendSimulator;`,
    `    private final GivenGameService givenGameService;`,
    `    private final GivenCardService givenCardService;`,
    `    private GameDTO gameDTO;`,
    ``,
    `    ${post.contextName}(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {`,
    `        this.frontendSimulator = frontendSimulator;`,
    `        this.givenGameService = givenGameService;`,
    `        this.givenCardService = givenCardService;`,
    `    }`,
    ``,
    `    public void beforeTest() throws Throwable {`,
    `        // Do your setup here`,
    `        givenGameService.givenGame("empty");`,
    `        givenCardService.givenCards(1, "Berry");`,
    `        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);`,
    `        `,
    `        // Please, verify that:`,
    `        // [ ] there are villagers, militia, ... that need berries? How many? How many moons?`,
    `        // [ ] is the empty game right for this post?`,
    `        throw new UnsupportedOperationException("Please, review the implementation of beforeTest() and remove this exception when it is correct.");`,
    `    }`,
  );
}

function makeContextBody(post) {
  return join(
    ...post.contextMethods.flatMap((m) => makeContextMethod(post, m)),
  );
}

function makeContextMethod(post, method) {
  const { name, arguments: args, text } = method;
  const formalArguments = args.map(({ name, type }) => `${type} ${name}`);
  const methodSignature = `${name}(${formalArguments.join(", ")})`;
  const closest = findClosestMethod(post, method);
  const lowerName = name.toLowerCase();

  return [
    ``,
    `    public void ${methodSignature} {`,
    `        // text: ${text}`,
    `        // code: this.${name}(${args.map((a) => a.value).join(", ")})`,
    // ...args.map(({ name, value }) => `        // ${name} = ${value}`),
    closest?.distance < 4 &&
      `        // hint: ${closest.post.contextName}.${closest.name}`,
    ``,
    makeContextMethodGiven(name),
    makeContextMethodEndMoon(lowerName),
    makeContextMethodExpected(args),
    ``,
    `        throw new UnsupportedOperationException("The method ${methodSignature} is not implemented yet.");`,
    `    }`,
  ];
}

function makeContextMethodGiven(name) {
  return (
    name.startsWith("given") && [
      "        // Add here what is given",
      "",
      "        // And make sure that the game is in the right state (also for the frontend)",
      '        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);',
    ]
  );
}

function makeContextMethodExpected(args) {
  return (
    args.some(({ name }) => name === "expected") && [
      `        var actual = expected; // FIXME`,
      `        assertThat(actual).isEqualTo(expected);`,
      ``,
    ]
  );
}

function makeContextMethodEndMoon(lowerName) {
  return (
    lowerName.includes("end") &&
    lowerName.includes("moon") && [
      '        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);',
    ]
  );
}

function makeContextFooter() {
  return join(
    ``,
    `    public void afterTest() {`,
    `        // Do your teardown here, if necessary`,
    `    }`,
    `}`,
  );
}

exports.writeJavaContextFile = writeJavaContextFile;
