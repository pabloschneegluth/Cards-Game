export const backend = {
  async get(url) {
    return fetch(url).then((r) => r.json());
  },
  async post(url, body) {
    return fetch(url, {
      method: "POST",
      body: JSON.stringify(body),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    }).then((r) => r.json());
  },
};
