const chokidar = require("chokidar");
const { glob } = require("glob");
const { startAuthors } = require("./create-tests/authors");
const { update } = require("./create-tests/update");
const { remove } = require("./create-tests/remove");
const { watchPath } = require("./create-tests/watchPath");
const path = require("path");
const chalk = require("chalk");

main();

async function main() {
  await verifyPath();
  await startAuthors();

  if (process.env.CI === "1") updateAll();
  else
    chokidar
      .watch(watchPath)
      .on("add", update)
      .on("change", update)
      .on("unlink", remove);
}

async function updateAll() {
  const files = glob.sync(watchPath + "/*.md");
  const updates = files.map(update);
  const posts = await Promise.all(updates);

  if (posts.some((p) => p.failed)) process.exit(1);
}

async function verifyPath() {
  const self = path.join(__dirname, "..", "..");
  const cwd = process.cwd();
  if (self !== cwd) {
    console.error(
      chalk.red(
        `Invalid current working directory: the current working directory must be the root of the project.\n` +
          `- the actual working directory is         : ${cwd}\n` +
          `- the expected working directory should be: ${self}\n` +
          `Please, change to the working directory and execute again create-tests.\n` +
          `$ cd ${self}\n` +
          `$ yarn create-tests\n`,
      ),
    );
    process.exit(1);
  }
}
