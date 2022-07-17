const { testJavaCoverage } = require("./test-coverage/testJavaCoverage");
const { testJsCoverage } = require("./test-coverage/testJsCoverage");

main();

async function main() {
  await testJavaCoverage();
  await testJsCoverage();
  console.log(`[test-coverage]: All coverages look good!`);
}
