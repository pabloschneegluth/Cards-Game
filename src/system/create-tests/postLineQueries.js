function isLineHeading(line) {
  return line.startsWith("#");
}
exports.isLineHeading = isLineHeading;

function isLineTable(line) {
  return line.startsWith("|");
}
exports.isLineTable = isLineTable;

function isLineStep(line) {
  return line.startsWith(" * ");
}
exports.isLineStep = isLineStep;
