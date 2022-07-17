class MethodStepParser {
  #text;
  #lineNumber;
  #index = 0;
  #methodName = "";
  #argumentValues = [];
  #argumentTypes = [];
  #argumentNames = [];
  #hintCounts = {};

  constructor(text, lineNumber) {
    this.#text = text;
    this.#lineNumber = lineNumber;
  }

  parse() {
    let lastIndex = -1;
    while (this.#index < this.#text.length && lastIndex !== this.#index) {
      lastIndex = this.#index;
      this.#skipSpaces();
      this.#acceptNameWord();
      this.#acceptStringArgument();
      this.#acceptNumberArgument();
    }

    return {
      name: this.#methodName,
      arguments: this.#argumentValues.map((value, i) => ({
        value,
        name: this.#argumentNames[i],
        type: this.#argumentTypes[i],
      })),
      text: this.#text,
      lineNumber: this.#lineNumber,
      isMethod: true,
    };
  }

  #skipSpaces() {
    this.#accept(/^[^a-z0-9"]+/i);
  }

  #acceptNameWord() {
    const word = this.#accept(/^[a-z]+/i);
    if (word) this.#appendNameWord(word[0]);
  }

  #acceptStringArgument() {
    const arg = this.#accept(/^"[^"]+"/);
    if (!arg) return;
    this.#appendArgument(arg[0], "s");
    this.#appendNameWord("S");
    this.#appendArgumentType("String");
  }

  #acceptNumberArgument() {
    const arg = this.#accept(/^[0-9]+/);
    if (!arg) return;
    this.#appendArgument(arg[0], "n");
    this.#appendNameWord("N");
    this.#appendArgumentType("int");
  }

  #appendNameWord(word) {
    this.#methodName +=
      this.#methodName.length === 0
        ? word.toLowerCase()
        : word[0].toUpperCase() + word.slice(1).toLowerCase();
  }

  #appendArgument(value, nameHint = "arg") {
    let count = this.#hintCounts[nameHint] || 1;
    let name = `${nameHint}${count}`;
    this.#hintCounts[nameHint] = count + 1;

    const isShouldBe = /shouldBe/i.test(this.#methodName);
    const isExpectNameFree = !this.#argumentNames.includes("expected");
    if (isShouldBe && isExpectNameFree) name = "expected";

    this.#argumentNames.push(name);
    this.#argumentValues.push(value);
  }

  #appendArgumentType(type) {
    this.#argumentTypes.push(type);
  }

  #accept(token) {
    const match = this.#matchToken(token);
    if (match) {
      this.#index += match[0].length;
      return match;
    }
    return undefined;
  }

  #matchToken(token) {
    const candidate = this.#text.slice(this.#index);
    return candidate.match(token);
  }
}

exports.MethodStepParser = MethodStepParser;
