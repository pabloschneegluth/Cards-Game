package com.drpicox.game.blog.api;

import com.drpicox.game.blog.Post;

import java.util.ArrayList;
import java.util.List;

public class ListPostsDTO {

    private List<ListPostsEntryDTO> posts = new ArrayList<>();

    public void addPost(Post post) {
        var entry = new ListPostsEntryDTO(post);
        posts.add(entry);
    }

    public List<ListPostsEntryDTO> getPosts() {
        return posts;
    }
}
