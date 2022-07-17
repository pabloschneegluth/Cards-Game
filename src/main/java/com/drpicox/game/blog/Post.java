package com.drpicox.game.blog;

import java.util.Map;
import java.util.Set;

public class Post implements Comparable<Post> {

    private final String id;
    private final String title;
    private final String body;
    private final Map<String, String> frontMatter;
    private final String md5;

    public Post(String postId, Map<String, String> frontMatter, String title, String body, String md5) {
        this.id = postId;
        this.frontMatter = frontMatter;
        this.title = title;
        this.body = body;
        this.md5 = md5;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getMd5() {
        return md5;
    }

    public String getValue(String key) {
        return frontMatter.get(key);
    }

    public Map<String, String> getFrontMatter() {
        return frontMatter;
    }

    public Set<String> getKeys() {
        return frontMatter.keySet();
    }

    @Override
    public int compareTo(Post o) {
        return -id.compareTo(o.id);
    }
}
