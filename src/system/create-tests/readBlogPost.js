const { readFile } = require("node:fs/promises");
const { MethodStepParser } = require("./MethodStepParser");
const { makeJavaName } = require("./makeJavaName");
const { watchPath } = require("./watchPath");
const md5 = require("md5");
const { preparsePostLines } = require("./preparsePostLines");
const { isLineStep, isLineHeading } = require("./postLineQueries");

async function readBlogPost(path) {
  const id = path.slice(watchPath.length + 1).split(".")[0];
  const javaName = makeJavaName(id);
  const testName = `Post_${javaName}_Test`;
  const contextName = `Post_${javaName}_Context`;

  const postContent = await readFile(path, "utf8");
  const postLines = postContent.split("\n");

  const testCalls = [];
  const tables = [];
  const contextMethods = [];

  const frontmatter = findFrontmatter(postLines);
  const titleIndex = findPostTitleIndex(postLines);
  const titleLineNumber = titleIndex + 1;
  const title = postLines[titleIndex];
  const hasCoder = findHasCoder(postLines);

  parseLines(postLines, testCalls, contextMethods, tables);

  const pkg = frontmatter.values.package;
  const subPath = pkg ? pkg.split(".") : [];

  const result = {
    id,
    title,
    titleLineNumber,
    path,
    hasCoder,
    lines: postLines,
    frontmatter,
    testName,
    testCalls,
    contextName,
    contextMethods,
    tables,
    subPath,
    subPackage: pkg ? `.${pkg}` : "",
    parent: subPath.length ? subPath.map(() => "..").join("/") : ".",
    md5: md5(postContent),
    failed: false,
  };
  return result;
}
exports.readBlogPost = readBlogPost;

function parseLines(postLines, testCalls, contextMethods, tables) {
  const linePairs = preparsePostLines(postLines, tables);

  linePairs.forEach(({ lineText, lineNumber }) => {
    parseLine(lineText, lineNumber, testCalls, contextMethods);
  });
}

function parseLine(postLine, lineNumber, testCalls, contextMethods) {
  if (isLineHeading(postLine)) {
    testCalls.push({ isComment: true, text: postLine });
    return;
  }

  if (!isLineStep(postLine)) return;
  const method = new MethodStepParser(postLine, lineNumber).parse();
  testCalls.push(method);

  if (contextMethods.every((m) => m.name !== method.name))
    contextMethods.push(method);
}

function findFrontmatter(lines) {
  const opening = lines[0].trim();
  const isOpeningOk = opening.startsWith("---");

  const closingIndex = findFrontmatterClosingIndex(lines);
  const isClosingOk = closingIndex !== -1;

  let values = Object.create(null);
  let valuesLines = {};
  let areValuesOk = true;
  let wrongValuesLines = [];

  for (let index = 1; index < closingIndex; index += 1) {
    const line = lines[index];
    const match = line.match(/^\s*([a-z_$-][a-z_0-9$-]*)\s*:\s*(.+)\s*/i);
    if (!match) {
      areValuesOk = false;
      wrongValuesLines.push({ lineNumber: index + 1, lineText: line });
    } else {
      valuesLines[match[1]] = index + 1;
      values[match[1]] = match[2].trim();
    }
  }

  return {
    opening,
    isOpeningOk,
    closingIndex,
    isClosingOk,
    values,
    valuesLines,
    areValuesOk,
    wrongValuesLines,
  };
}

function findFrontmatterClosingIndex(lines) {
  for (let index = 1; index < lines.length; index++) {
    if (lines[index].trim().startsWith("---")) return index;
  }
  return -1;
}

function findPostTitleIndex(lines) {
  return lines.findIndex((l) => l.startsWith("# "));
}

function findHasCoder(lines) {
  for (
    let index = 1;
    index < lines.length && !lines[index].startsWith("---");
    index += 1
  ) {
    if (lines[index].match(/^\s*coder\s*:/)) return true;
  }

  return false;
}
