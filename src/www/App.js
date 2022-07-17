import { Routes, Route } from "react-router-dom";
import { Header } from "./layout/Header";
import { BlogPage } from "./blog/BlogPage";
import { GamePage } from "./game/GamePage";
import { PostPage } from "./blog/PostPage";
import { HomePage } from "./HomePage";

export function App() {
  return (
    <div>
      <Header />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/blog" element={<BlogPage />} />
        <Route path="/blog/:postId" element={<PostPage />} />
        <Route path="/game" element={<GamePage />} />
      </Routes>
    </div>
  );
}
