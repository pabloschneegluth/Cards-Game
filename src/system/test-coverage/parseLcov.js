function parseLcov(lcov) {
  const lines = lcov.split("\n");
  const result = [];

  let file = "-";
  let current = newCovFile("-");

  for (const line of lines) {
    const [type, data] = line.split(":");
    const fields = data?.split(",");
    switch (type) {
      case "SF":
        file = data;
        current = newCovFile(file);
        result.push(current);
        break;
      case "FNF":
        current.functions.found = +data;
        break;
      case "FNH":
        current.functions.hit = +data;
        break;
      case "BRF":
        current.branches.found = +data;
        break;
      case "BRH":
        current.branches.hit = +data;
        break;
      case "LF":
        current.lines.found = +data;
        break;
      case "LH":
        current.lines.hit = +data;
        break;
      case "FN":
        current.functions.linesByName[fields[1]] = +fields[0];
        break;
      case "FNDA": {
        const [hit, name] = data.split(",");
        if (+hit !== 0) break;
        current.misses.push({
          type: "function",
          name,
          file,
          line: current.functions.linesByName[name],
        });
        break;
      }
      case "BRDA": {
        const [line, , , taken] = fields;
        if (+taken !== 0) break;
        current.misses.push({
          type: "branch",
          file,
          line: +line,
        });
        break;
      }
      case "DA": {
        const [line, hit] = fields;
        if (+hit !== 0) break;
        current.misses.push({
          type: "line",
          file,
          line: +line,
        });
        break;
      }
      default:
      // ignore
    }
  }

  return result;
}
exports.parseLcov = parseLcov;
function newCovFile(fileName) {
  const file = {
    file: fileName,
    lines: {},
    functions: { linesByName: {} },
    branches: {},
    misses: [],
  };
  return file;
}
