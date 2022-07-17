exports.PostSectionTable = class PostSectionTable {
  #heading = null;
  #headingText = null;
  #separatorText = "|---|";
  #rows = [];
  #rowsText = [];

  isEmpty() {
    return this.#rows.length === 0;
  }

  parseLine(line) {
    if (line.includes("---")) {
      this.#separatorText = line;
      return;
    }

    let columns = line
      .split("|")
      .map((s) => s.trim())
      .slice(1, -1);

    if (this.#heading == null) {
      this.#heading = columns;
      this.#headingText = line;
      return;
    }

    let row = { substitutions: [], rowText: line };
    for (let i = 0; i < this.#heading.length; i++) {
      let columnName = this.#heading[i];
      row.substitutions.push({ replace: `${columnName}`, by: columns[i] });
    }
    this.#rows.push(row);
  }

  interpolate(sectionPairs) {
    if (this.#rows.length === 0 || sectionPairs.length === 0)
      return sectionPairs;

    let result = [];
    for (let row of this.#rows) {
      result.push(...this.#interpolateRow(row, sectionPairs));
    }
    return result;
  }

  #interpolateRow = ({ substitutions, rowText }, sectionPairs) => {
    let firstLineNumber = sectionPairs[0].lineNumber;
    let separator = sectionPairs[0].lineText.match(/^#+/)?.[0] || "####";
    separator += "# ";

    let result = [];
    for (let sectionPair of sectionPairs) {
      let line = sectionPair.lineText;
      let lineNumber = sectionPair.lineNumber;
      for (let { replace, by } of substitutions) {
        line = line.replace(replace, by);
      }
      result.push({ lineText: line, lineNumber });
    }

    result.splice(
      1,
      0,
      { lineText: separator + this.#headingText, lineNumber: firstLineNumber },
      {
        lineText: separator + this.#separatorText,
        lineNumber: firstLineNumber,
      },
      { lineText: separator + rowText, lineNumber: firstLineNumber },
    );

    return result;
  };
};
