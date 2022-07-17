package com.drpicox.game.synthetics;

import com.drpicox.game.blog.AuthorsService;
import com.drpicox.game.blog.api.ListPostsDTO;
import com.drpicox.game.blog.api.ListPostsEntryDTO;
import com.drpicox.game.blog.api.PostDTO;
import com.drpicox.game.constants.ConstantsLoader;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private Gson gson;
    @Autowired private AuthorsService authorsService;
    @Autowired private ConstantsLoader constantsLoader;

    @Test
    public void there_should_be_more_than_one_author() {
        var users = authorsService.getGitHubUsers();
        assertThat(users.size()).isGreaterThan(1);
    }

    @Test
    public void the_post_service_list_all_the_posts() throws Throwable {
        var list = fetchFromRestController("/api/v1/posts", ListPostsDTO.class);
        var posts = list.getPosts();

        assertThat(posts.size()).isAtLeast(1);

        var entry = posts.get(posts.size() - 1);
        assertThat(entry.getId()).isEqualTo("2022-07-15_hello_blog");
        assertThat(entry.getTitle()).isEqualTo("Hello Blog");
        assertThat(entry.getProperty("writer")).isEqualTo("drpicox");
    }

    @Test
    public void the_post_service_reads_one_post() throws Throwable {
        var post = fetchFromRestController("/api/v1/posts/2022-07-15_hello_blog", PostDTO.class);

        assertThat(post.getId()).isEqualTo("2022-07-15_hello_blog");
        assertThat(post.getTitle()).isEqualTo("Hello Blog");
        assertThat(post.getBody()).contains("\"this text\"");
    }

    private static final Set<String> acceptedFrontMatterKeys = new LinkedHashSet<>(){{
        add("writer");
        add("coder");
        add("package");
    }};

    @Test
    public void posts_only_can_contain_the_frontmatter_accepte_keys__no_other_keys_are_accepted() throws Throwable {
        forEachPost(post -> {
            var id = post.getId();
            var frontMatter = post.getFrontMatter();

            var offendingKeys = new LinkedHashSet<>(frontMatter.keySet());
            offendingKeys.removeAll(acceptedFrontMatterKeys);
            if (offendingKeys.size() == 0) return;

            throw new AssertionError("Post '" + id + ".md' should have only 'writer' and 'coder' in the frontMatter.\n" +
                    "post id         : " + id + "\n" +
                    "post title      : " + post.getTitle() + "\n" +
                    "actual keys are : " + frontMatter.keySet() + "\n" +
                    "expected key are: " + acceptedFrontMatterKeys.stream().collect(Collectors.joining(", ")) + "\n" +
                    "Please, verify that there is no extra keys in the frontmatter.\n" +
                    (offendingKeys.contains("debug") ? "IMPORTANT: the debug key should be removed, its purpose is only for local execution, not for deployment.\n" : "" )+
                    "Example:\n" +
                    "  ---\n" +
                    frontMatter.keySet().stream().map(key -> {
                       var result =  "  " + key + ": " + frontMatter.get(key);
                       if (!acceptedFrontMatterKeys.contains(key))
                           result += "  << remove this key, or fix the spelling";
                       result += "\n";
                       return result;
                    }).collect(Collectors.joining()) +
                    "  ---\n" +
                    "  # " + post.getTitle() + "\n" +
                    "  ..."
            );
        });
    }

    @Test
    public void posts_writer_and_coder_values_must_correspond_to_actual_github_users_saved_in_the_resources_authors_properties_file() throws Throwable {
        forEachPost(post -> {
            var keys = new String[]{ "writer", "coder" };
            for (var key: keys) {
                var value = post.getProperty(key);
                if (value == null) return;

                var isValid = authorsService.containsGitHubUser(value);
                if (isValid) return;

                var id = post.getId();
                throw new AssertionError("Post '" + id + ".md' frontmatter key '"+key+"' value must match one of the github users defined in the \"resources/authors.properties\" file.\n" +
                        "post id                  : " + id + "\n" +
                        "post title               : " + post.getTitle() + "\n" +
                        "actual github user is    : " + value + "\n" +
                        "expected github users are: " + authorsService.getGitHubUsers().stream().collect(Collectors.joining(", ")) + "\n" +
                        "Please, check that:\n" +
                        "- the naming is right, correct the spelling if necessary,\n" +
                        "- the github user is in the file, add it if it is not present,\n"
                );
            }
        });
    }

    @Test
    public void posts_writer_and_coder_cannot_have_the_same_value() throws Throwable {
        forEachPost(post -> {
            var coder = post.getProperty("coder");
            if (coder == null) return;

            var writer = post.getProperty("writer");
            if (!coder.equals(writer) || coder.equals("drpicox")) return;

            var id = post.getId();
            throw new AssertionError("Post '" + id + ".md' frontmatter coder and writer have the same value '"+coder+"'.\nThe writer of a post cannot be its coder.\n" +
                    "post id       : " + id + "\n" +
                    "post title    : " + post.getTitle() + "\n" +
                    "actual writer : " + writer + "\n" +
                    "actual coder  : " + coder + "\n" +
                    "expected coder: _a coder different from '"+coder+"'_\n" +
                    "Please, check that:\n" +
                    "- you added correctly your github username as coder,\n" +
                    "- the actual writer is the real writer of the post,\n" +
                    "- if you are the writer and post, look for another post to solve,\n" +
                    "You cannot develop your own posts.\n"
            );
        });
    }

    private <T> T fetchFromRestController(String url, Class<T> type) throws Exception {
        var result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var list = gson.fromJson(result, type);
        return list;
    }

    private void forEachPost(TestPost consumer) throws Throwable {
        var list = fetchFromRestController("/api/v1/posts", ListPostsDTO.class);
        for (var post: list.getPosts()) {
            consumer.test(post);
        }
    }

    interface TestPost {
        void test(ListPostsEntryDTO post) throws Throwable;
    }
}
