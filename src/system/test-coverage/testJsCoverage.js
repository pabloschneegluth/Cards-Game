const open = require("open");
const chalk = require("chalk");
const { readFile } = require("node:fs/promises");
const { readFileSync } = require("node:fs");
const { codeFrameColumns } = require("@babel/code-frame");
const path = require("node:path");
const { parseLcov } = require("./parseLcov");

const coveragePath = path.join(__dirname, "..", "..", "..", "coverage");

async function testJsCoverage() {
  const coverages = await readJsCoverages();
  const uncoverages = findUncovered(coverages);

  const isEverythingCovered = uncoverages.length === 0;
  if (isEverythingCovered) return;

  reportJsUncoverages(uncoverages);
  hintAndOpenHtmlReport();

  process.exit(1);
}

exports.testJsCoverage = testJsCoverage;

async function readJsCoverages() {
  const jsCoverageData = await readFile(
    path.join(coveragePath, "lcov.info"),
    "utf8",
  );

  const result = parseLcov(jsCoverageData);
  return result;
}

function findUncovered(coverages) {
  return coverages.filter(
    (c) =>
      c.lines.found !== c.lines.hit ||
      c.functions.found !== c.functions.hit ||
      c.branches.found !== c.branches.hit,
  );
}

function reportJsUncoverages(uncoverages) {
  console.error(
    chalk.red.bold("[test-coverage] ERROR: Found uncovered Java code:"),
  );

  uncoverages.forEach(reportUncovered);
}

function reportUncovered(uncovered) {
  console.error(
    `\n${chalk.bold("Failed")} JavaScript Coverage: ${chalk.bold(
      uncovered.file,
    )}`,
  );

  console.error(
    [
      `- Lines: ${uncovered.lines.hit}/${uncovered.lines.found}`,
      `- Functions: ${uncovered.functions.hit}/${uncovered.functions.found}`,
      `- Branches: ${uncovered.branches.hit}/${uncovered.branches.found}`,
    ].join("\n"),
  );

  uncovered.misses.forEach((miss) => {
    console.error(
      chalk.dim(
        `\nMissed ${miss.type}${
          miss.name ? ` ${miss.name}` : ""
        } coverage, at ${miss.file}:${miss.line}`,
      ),
    );
    console.error(getCodeFrame(miss.file, miss.line));
  });
}

function hintAndOpenHtmlReport() {
  const htmlPath = path.join(coveragePath, "lcov-report", "index.html");
  console.error(chalk.blue(`\nHint: Open the HTML report at ${htmlPath}\n`));

  if (process.env.CI !== "1") open(htmlPath);
}

function getCodeFrame(filename, line) {
  let rawFileContents = "";
  try {
    rawFileContents = readFileSync(filename, "utf-8");
  } catch {
    return "";
  }

  const codeFrame = codeFrameColumns(
    rawFileContents,
    { start: { line } },
    { highlightCode: true },
  );
  return `${codeFrame}`;
}
