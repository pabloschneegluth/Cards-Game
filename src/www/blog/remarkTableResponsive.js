import { visit } from "unist-util-visit";

export function remarkTableResponsive() {
  return (tree) => {
    visit(tree, "table", (node, index, parent) => {
      parent.children[index] = {
        type: "element",
        tagName: "div",
        properties: {
          className: ["table-responsive"],
        },
        children: [node],
      };
    });
  };
}
