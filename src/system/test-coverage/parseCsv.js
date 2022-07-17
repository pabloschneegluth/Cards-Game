function parseCsv(csv) {
  const lines = csv
    .split("\n")
    .map((l) => l.trim())
    .filter(Boolean);

  const headers = lines[0].split(",");
  const rows = lines.slice(1).map((line) => line.split(","));

  const result = [];
  rows.forEach((row) => {
    const obj = {};
    row.forEach((value, index) => {
      if (/^\d+$/.test(value)) value = +value;
      obj[headers[index]] = value;
    });
    result.push(obj);
  });
  return result;
}
exports.parseCsv = parseCsv;
