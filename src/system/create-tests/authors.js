const chalk = require("chalk");
const chokidar = require("chokidar");
const { readFile } = require("node:fs/promises");
const path = require("path");

const authorsPathParts = ["src", "main", "resources", "authors.properties"];
const authorsWatchPath = authorsPathParts.join("/");
const authorsPath = path.join(__dirname, "..", "..", "..", ...authorsPathParts);

let authors = Object.create(null);

async function startAuthors() {
  await loadAuthors();
  if (process.env.CI === "1") return;

  chokidar
    .watch(authorsWatchPath)
    .on("add", updateAuthors)
    .on("change", updateAuthors);
}

exports.startAuthors = startAuthors;

function isAuthorValid(username) {
  return username in authors;
}

exports.isAuthorValid = isAuthorValid;

function getAuthorUsernames() {
  return Object.keys(authors);
}

exports.getAuthorUsernames = getAuthorUsernames;

async function loadAuthors() {
  const color = chalk.magenta;
  console.log(
    `[${new Date().toLocaleTimeString("ca")}] ${color.inverse(
      " Authors "
    )} ${color(authorsWatchPath)}`
  );

  const authorsContent = await readFile(authorsPath, "utf8");
  const authorsLines = authorsContent.split("\n");

  authors = {};
  authorsLines.forEach((line) => {
    const [user, name] = line.split("=");
    if (user && name) authors[user] = name;
  });
}

let updated = false;
function updateAuthors() {
  if (!updated) {
    updated = true;
    return;
  }

  loadAuthors();
  console.log(
    `${chalk.magenta.inverse(" Warning ")} ${chalk.magenta(
      `The authors file has been updated, but several posts have been already validated.\n          Please, restart create-tests to verify old posts.`
    )}`
  );
}
