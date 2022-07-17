const { isLineHeading, isLineTable } = require("./postLineQueries");
const { PostSectionTable } = require("./PostSectionTable");

exports.preparsePostLines = function preparsePostLines(postLines, tables) {
  let result = [];
  let lastHeading = "# ";

  let table = new PostSectionTable();
  let sectionPairs = [];
  for (let index = 0; index < postLines.length; index += 1) {
    const lineText = postLines[index];
    const lineNumber = index + 1;

    if (isLineHeading(lineText)) {
      if (table.isEmpty() || isEqualOrHigherHeading(lineText, lastHeading)) {
        if (!table.isEmpty()) tables.push(table);
        lastHeading = lineText;
        result.push(...table.interpolate(sectionPairs));
        table = new PostSectionTable();
        sectionPairs = [];
      }
    }

    if (isLineTable(lineText)) {
      table.parseLine(lineText);
    }

    sectionPairs.push({ lineText, lineNumber });
  }
  if (!table.isEmpty()) tables.push(table);
  result.push(...table.interpolate(sectionPairs));

  return result;
};

function isEqualOrHigherHeading(lineText, lastHeading) {
  if (!isLineHeading(lineText)) return false;

  const currentHashes = lineText.match(/^#+ /)?.[0];
  const lastHashes = lastHeading.match(/^#+ /)?.[0];

  return currentHashes.length <= lastHashes.length;
}
