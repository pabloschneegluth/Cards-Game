package com.drpicox.game.synthetics;

import com.drpicox.game.blog.IllegalPostFileFormatException;
import com.drpicox.game.blog.Post;
import com.drpicox.game.blog.PostParser;
import com.drpicox.game.constants.Constants;
import com.drpicox.game.constants.ConstantsCollection;
import com.drpicox.game.constants.ConstantsLoader;
import com.drpicox.game.util.OneCollector;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HintsForStudentsTest {

    @Test
    public void parses_successfully_a_blog_post() throws Throwable {
        var post = parsePostContent(
                "2010-01-01_title",
                "---",
                "akey: avalue",
                "bkey: bvalue ",
                "---",
                " ",
                "# Title ",
                "",
                "first line  ",
                " second line"
        );

        assertThat(post.getId()).isEqualTo("2010-01-01_title");
        assertThat(post.getValue("akey")).isEqualTo("avalue");
        assertThat(post.getValue("bkey")).isEqualTo("bvalue");
        assertThat(post.getTitle()).isEqualTo("Title");
        assertThat(post.getBody()).isEqualTo("first line  \n second line\n");
    }

    @Test
    public void front_matter_allows_spaces_and_comments() throws Throwable {
        var post = parsePostContent("2010-01-01_title", "---", "foo: bar", "    ", "# some comment", "other: value", "---", "# Title", "content");

        assertThat(post.getId()).isEqualTo("2010-01-01_title");
        assertThat(post.getValue("foo")).isEqualTo("bar");
        assertThat(post.getValue("other")).isEqualTo("value");
        assertThat(post.getKeys()).containsExactly("foo", "other");
        assertThat(post.getTitle()).isEqualTo("Title");
        assertThat(post.getBody()).isEqualTo("content\n");
    }

    @Test public void if_post_title_starts_with_non_chars_it_does_not_become_an_extra___underscore() throws Throwable {
        var post = parsePostContent("2010-01-01_title", "---", "foo: bar", "---", "# !Title", "content");
        assertThat(post.getId()).isEqualTo("2010-01-01_title");
    }

    @Test public void failures_in_parsing() throws Throwable {
        assertThatThrows();
        assertThatThrows("2021-2-10_title", "---", "x:a", "---", "# title");
        assertThatThrows("2021-20-10_title", "---", "x:a", "---", "# title");
        assertThatThrows("2021-10-4_title", "---", "x:a", "---", "# title");
        assertThatThrows("2021-10-40_title", "---", "x:a", "---", "# title");
        assertThatThrows("2021-10-10_wrong_title", "---", "x:a", "---", "# Right title");
        assertThatThrows("2021-10-10_wrongid", "---", "x:a", "---", "# Wrong Id");
        assertThatThrows("2021-10-10_wrong_Id", "---", "x:a", "---", "# Wrong Id");
        assertThatThrows("---", "x", "---", "# title");
        assertThatThrows("no frontmatter", "---", "title:x", "---");
        assertThatThrows("---","foo: no frontmatter end", "other: prop");
        assertThatThrows("---","unexpected frontmatter line", "---", "bla");
        assertThatThrows("---","other:prop", "---", "no title");
        assertThatThrows("---","only: frontmatter", "---", "");
    }

    @Test public void loading_an_unknown_card_throws_an_exception() throws IOException, URISyntaxException {
        var constantsLoader = new ConstantsLoader();
        var cards = constantsLoader.loadCollection("card");
        Throwable found = null;
        try {
            cards.getByName("CardThatDoesNotExists");
        } catch (Throwable th) {
            found = th;
        }

        assertThat(found).isNotNull();
        assertThat(found.toString()).contains("card");
        assertThat(found.toString()).contains("src/main/resources/card");
        assertThat(found.toString()).contains("CardThatDoesNotExists");
    }

    @Test public void loading_twice_a_property_fail_with_the_same_name_fails() throws IOException, URISyntaxException {
        var collection = new ConstantsCollection("demo");
        Throwable found = null;
        try {
            collection.add(newConstants("Repeated"), "some/path.properties");
            collection.add(newConstants("Repeated"), "some/other/path.properties");
        } catch (Throwable th) {
            found = th;
        }

        assertThat(found).isNotNull();
        assertThat(found.toString()).contains("demo");
        assertThat(found.toString()).contains("Repeated");
        assertThat(found.toString()).contains("some/path.properties");
        assertThat(found.toString()).contains("some/other/path.properties");
        assertThat(found.toString()).contains("src/main/resources/demo");
    }

    @Test public void all_property_files_with_constants_must_have_name() throws IOException, URISyntaxException {
        var collection = new ConstantsCollection("demo");
        Throwable found = null;
        try {
            collection.add(new Constants(new HashMap<>()), "some/path.properties");
        } catch (Throwable th) {
            found = th;
        }

        assertThat(found).isNotNull();
        assertThat(found.toString()).contains("does not have name");
        assertThat(found.toString()).contains("demo");
        assertThat(found.toString()).contains("some/path.properties");
        assertThat(found.toString()).contains("src/main/resources/demo");
    }

    @Test public void one_collector_fails_if_too_much_elements() {
        Throwable found = null;
        try {
            var l = List.of("a", "b");
            l.stream().collect(OneCollector.toOne());
        } catch (Throwable th) {
            found = th;
        }
        assertThat(found).isNotNull();
    }

    @Test public void one_collector_fails_if_none_elements() {
        Throwable found = null;
        try {
            var l = List.of();
            l.stream().collect(OneCollector.toOne());
        } catch (Throwable th) {
            found = th;
        }
        assertThat(found).isNotNull();
    }

    private static Constants newConstants(String name) {
        var props = new HashMap();
        props.put("name", name);
        var constants = new Constants(props);
        return constants;
    }

    private void assertThatThrows(String ...lines) throws Throwable {
        IllegalPostFileFormatException throwable = null;
        try {
            parsePostContent(lines);
        } catch (IllegalPostFileFormatException exception) {
            throwable = exception;
        }
        assertThat(throwable).isNotNull();
    }

    private static Post parsePostContent(String ...lines) throws NoSuchAlgorithmException {
        String postId;
        if (lines.length > 0 && lines[0].charAt(0) == '2') {
            postId = lines[0];
            lines = Arrays.copyOfRange(lines, 1, lines.length);
        } else {
            postId = "2010-01-01_title";
        }

        var parser = new PostParser(postId, Arrays.asList(lines), new byte[]{});
        return parser.parse();
    }

}
