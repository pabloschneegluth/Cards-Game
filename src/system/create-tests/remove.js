const chalk = require("chalk");
const { removePost } = require("./posts");

async function remove(filePath) {
  const id = filePath.split("/").at(-1).split("\\").at(-1).split(".").at(-2);

  removePost(id);

  console.log(
    `[${new Date().toLocaleTimeString("ca")}] ${chalk.grey.inverse(
      " REMOVED ",
    )} ${filePath}`,
  );
}

exports.remove = remove;
