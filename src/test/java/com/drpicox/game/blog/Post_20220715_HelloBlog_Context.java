package com.drpicox.game.blog;

import com.drpicox.game.blog.api.ListPostsDTO;
import com.drpicox.game.blog.api.ListPostsEntryDTO;
import com.drpicox.game.blog.api.PostDTO;
import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;

import java.util.Optional;

@Component
public class Post_20220715_HelloBlog_Context {

    private final FrontendSimulator frontendSimulator;

    private ListPostsDTO postsListDTO;
    private PostDTO postDTO;

    Post_20220715_HelloBlog_Context(FrontendSimulator frontendSimulator) {
        this.frontendSimulator = frontendSimulator;
    }

    public void beforeTest() {
    }

    public void goToTheBlogSection() {
        postsListDTO = frontendSimulator.get("/api/v1/posts", ListPostsDTO.class);
    }

    public void youShouldSeeAListOfPosts() {
        assertThat(postsListDTO.getPosts()).isNotEmpty();
    }

    public void theLastPostTitleShouldBeSThisPost(String expected) {
        // expected = "Hello Blog"
        var query = findPostEntry(expected);
        assertThat(query).isPresent();
    }

    public void goToTheSPost(String the) {
        // the = "Hello Blog"
        var entry = findPostEntry(the);
        var id = entry.get().getId();
        postDTO = frontendSimulator.get("/api/v1/posts/" + id, PostDTO.class);
    }

    public void youShouldSeeTheSPost(String the) {
        assertThat(postDTO.getTitle()).isEqualTo(the);
    }

    public void thePostShouldContainSWhichIsHere(String contain) {
        assertThat(postDTO.getBody()).contains(contain);
    }

    private Optional<ListPostsEntryDTO> findPostEntry(String expectedTitle) {
        return postsListDTO.getPosts().stream().filter(p -> p.getTitle().equals(expectedTitle)).findAny();
    }

    public void afterTest() {
    }
}
