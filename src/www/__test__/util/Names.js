export class Names {
  #list = [];

  static byNames(...args) {
    const names = new Names();
    names.and(...args);
    return names;
  }

  and(...args) {
    if (args.length === 2 && typeof args[0] === "number") {
      return this.#andCount(args[0], args[1]);
    }

    this.#list.push(...args);
    return this;
  }

  get() {
    return this.#list;
  }

  #andCount(count, name) {
    for (let i = 0; i < count; i++) {
      this.#list.push(name);
    }
    return this;
  }
}
