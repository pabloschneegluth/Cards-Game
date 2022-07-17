const prettier = require("prettier");

function prettyJs(code) {
  try {
    return prettier.format(code, { parser: "babel" });
  } catch (e) {
    console.error(e);
    return code;
  }
}
exports.prettyJs = prettyJs;
