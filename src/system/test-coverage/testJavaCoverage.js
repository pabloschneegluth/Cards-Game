const open = require("open");
const chalk = require("chalk");
const { readFile } = require("node:fs/promises");
const path = require("node:path");
const { parseCsv } = require("./parseCsv");

const jacocoPath = path.join(
  __dirname,
  "..",
  "..",
  "..",
  "target",
  "site",
  "jacoco",
);

async function testJavaCoverage() {
  const coverages = await readJavaCoverages();
  const uncoverages = findUncovered(coverages);

  const isEverythingCovered = uncoverages.length === 0;
  if (isEverythingCovered) return;

  reportUncoverages(uncoverages);
  hintAndOpenHtmlReport();

  process.exit(1);
}

exports.testJavaCoverage = testJavaCoverage;

function hintAndOpenHtmlReport() {
  const htmlPath = path.join(jacocoPath, "index.html");
  console.error(chalk.blue(`\nHint: Open the HTML report at ${htmlPath}\n`));

  if (process.env.CI !== "1") open(htmlPath);
}

function reportUncoverages(uncoverages) {
  console.error(
    chalk.red.bold("[test-coverage] ERROR: Found uncovered Java code:"),
  );

  uncoverages.forEach(reportUncovered);
}

function reportUncovered(uncovered) {
  console.error(
    `\n${chalk.bold("Failed")} Java Coverage: ${uncovered.PACKAGE}.${chalk.bold(
      uncovered.CLASS,
    )}`,
  );

  Object.keys(uncovered)
    .filter((metric) => metric.endsWith("_MISSED"))
    .forEach((metric) => {
      const covered = uncovered[metric.replace("_MISSED", "_COVERED")];
      const missed = uncovered[metric];
      const total = covered + missed;

      const color = missed === 0 ? (i) => i : chalk.red;
      console.error(color(`  ${metric.split("_")[0]}: ${covered}/${total}`));
    });
}

function findUncovered(coverages) {
  return coverages.filter(
    (f) =>
      f.CLASS !== "ClassroomCardsGame2022Application" &&
      Object.keys(f).some((k) => k.endsWith("_MISSED") && f[k] !== 0),
  );
}

async function readJavaCoverages() {
  const javaCoverageData = await readFile(
    path.join(jacocoPath, "jacoco.csv"),
    "utf8",
  );
  const coverages = parseCsv(javaCoverageData);
  return coverages;
}
