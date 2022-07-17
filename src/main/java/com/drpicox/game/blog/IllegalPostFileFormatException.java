package com.drpicox.game.blog;

public class IllegalPostFileFormatException extends IllegalArgumentException {
    public IllegalPostFileFormatException(String postId, int line, String message) {
        super("Error reading the post:line \"" + postId + ".md:" + line + "\": found a problem in the format while parsing the post.\n" + message);
    }
}
