const { methodNameDistance } = require("./methodNameDistance");

const posts = {};

function removePost(postId) {
  delete posts[postId];
}
exports.removePost = removePost;

function registerPost(post) {
  posts[post.id] = post;

  // Invalidate memoizations
  memoizedPost = null;
}
exports.registerPost = registerPost;

function findClosestMethod(post, method) {
  const usages = findOtherPostsMethodUsages(post);
  const targetName = method.name;

  if (usages.length === 0) return null;

  let candidate = usages[0];
  let candidateDistance = methodNameDistance(candidate.name, targetName);
  for (let i = 1; i < usages.length; i += 1) {
    let newCandidate = usages[i];
    let newDistance = methodNameDistance(newCandidate.name, targetName);
    if (newDistance < candidateDistance) {
      candidate = newCandidate;
      candidateDistance = newDistance;
    }
  }

  return { ...candidate, distance: candidateDistance };
}
exports.findClosestMethod = findClosestMethod;

let memoizedPost = null;
let memoizedResult = null;
function findOtherPostsMethodUsages(post) {
  if (memoizedPost === post) return memoizedResult;
  let posts = findOtherPosts(post);
  posts = posts.slice().sort().reverse();
  const usages = {};
  posts.forEach((post) => {
    post.contextMethods.forEach((method) => {
      if (!usages[method.name]) {
        usages[method.name] = {
          ...method,
          post,
          postIds: [],
        };
      }
      usages[method.name].postIds.push(post.id);
    });
  });

  memoizedPost = post;
  memoizedResult = Object.values(usages);
  return memoizedResult;
}

function findOtherPosts(post) {
  return Object.values(posts).filter((p) => p.id !== post.id);
}
