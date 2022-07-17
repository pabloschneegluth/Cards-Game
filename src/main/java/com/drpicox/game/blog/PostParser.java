package com.drpicox.game.blog;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PostParser {
    private final String postId;
    private String title;
    private final StringBuilder body = new StringBuilder();
    private final Map<String, String> frontMatter = new LinkedHashMap<>();

    private final List<String> lines;
    private int lineIndex;

    private byte[] rawContent;

    public PostParser(String postId, List<String> lines, byte[] rawContent) {
        this.postId = postId;
        this.lines = lines;
        this.rawContent = rawContent;
    }

    public Post parse() throws NoSuchAlgorithmException {
        parseFrontMatter();
        skipEmptyLines();
        parseTitle();
        skipEmptyLines();
        parseBody();

        var md5 = computeMd5(lines.stream().collect(Collectors.joining("\n")));

        return new Post(postId, frontMatter, title, body.toString(), md5);
    }

    private void parseFrontMatter() {
        expectNotEof("the post should not be empty");
        expectPeekStartWith("---", "the first line of the post should begin with the \"---\" of the frontMatter");
        nextLine();
        while (!isPeekStartingWith("---")) {
            parseFrontMatterLine();
            expectNotEof("the frontMatter should end before reaching the end of file, did you miss adding the \"---\" after the frontMatter data?");
        }
        nextLine();
    }

    private void parseFrontMatterLine() {
        var line = peekLine();
        if (line.matches("^(\\s*|#.*)$")) {
            nextLine();
            return;
        }

        if (!line.matches("^[A-Za-z0-9._]+:.*$"))
            throw newError("the frontMatter content should be \"key: value\"");

        var indexOfColon = line.indexOf(':');
        var key = line.substring(0, indexOfColon);
        var value = line.substring(indexOfColon + 1).trim();
        frontMatter.put(key, value);
        nextLine();
    }

    private void parseTitle() {
        expectNotEof("The title, \"# Your title here\" should be present after the frontMatter, but it does not appears before reaching the end of file");
        var line = peekLine();
        if (!line.startsWith("# "))
            throw newError("The title, \"# Your title here\" should be the first thing present after the frontMatter, but found \"" + line + "\" instead");

        title = line.substring(1).trim();
        validateTitle();

        nextLine();
    }

    private void validateTitle() {
        if (!postId.matches("^20\\d\\d-[01]\\d-[0123]\\d_.*"))
            throw new IllegalPostFileFormatException(postId, lineIndex,
                    "The post file id was \""+postId+"\" and it does not begin with a date like 20YY-MM-DD_. Please check that:\n" +
                            "- It has the four digit year number,\n" +
                            "- It has a two digit month number, starting with 0 if not october or later,\n" +
                            "- It has a two digit day number, starting with 0 if earlier than the 10th,\n" +
                            "- There is a dash (minus sign) after the year,\n" +
                            "- There is a dash (minus sign) after the month,\n" +
                            "- There is an underscore after the day"
            );

        var expected = Arrays.stream(title.toLowerCase().split("[^a-z0-9]+")).collect(Collectors.joining("_"));
        if (expected.startsWith("_")) {
            expected = expected.substring(1);
        }
        expected = postId.substring(0,11) + expected;

        if (!expected.equals(postId))
            throw newError("The post file id was \""+postId+"\" but it does not match the expected \""+expected+"\". Please check that:\n" +
                        "- The post tile (# title) and the postId contains the same text,\n" +
                        "- The postId is in lower case,\n" +
                        "- That all spaces and non latin letters (no a-z), and not numbers, are replaced by underscore _,\n" +
                        "- That there are not two consecutive underscores,\n" +
                        "If the post title is correct, you can rename the post as follows:\n" +
                        "=> from \""+postId+".md\"\n" +
                        "=> to   \""+expected+".md\""
                    );
    }

    private void parseBody() {
        while (isNotEof()) {
            body.append(nextLine()).append('\n');
        }
    }

    private void skipEmptyLines() {
        while (isNotEof() && isPeekEmptyLine()) {
            nextLine();
        }
    }

    private boolean isNotEof() {
        return lineIndex < lines.size();
    }

    private boolean isPeekStartingWith(String prefix) {
        return peekLine().startsWith(prefix);
    }

    private boolean isPeekEmptyLine() {
        return peekLine().matches("^\\s*$");
    }

    private String nextLine() {
        var line = peekLine();
        lineIndex += 1;
        return line;
    }

    private String peekLine() {
        return lines.get(lineIndex);
    }

    private void expectNotEof(String message) {
        if (isNotEof()) return;
        throw newError("Unexpectedly reached end of file, " + message);
    }

    private void expectPeekStartWith(String prefix, String message) {
        if (peekLine().startsWith(prefix)) return;
        throw newError("The line should begin with \"" + prefix+ "\", " + message);
    }

    private IllegalPostFileFormatException newError(String message) {
        int lineNumber = lineIndex + 1;
        var details = new StringBuffer();
        details.append("\n  ").append(message).append(".\n");
        details.append("The error source is at: ");
        details.append(postId).append(".md, line: ").append(lineNumber).append(":\n");

        var top = Math.max(0, lineIndex - 3);
        var bottom = Math.min(lines.size(), lineIndex + 3);
        if (top != 0) details.append("       ...\n");
        for (var index = top; index < bottom; index += 1) {
            details.append("   ");
            if (index == lineIndex) details.append(">>> "); else details.append("    ");
            details.append(lines.get(index));
            details.append("\n");
        }
        if (bottom != lines.size()) details.append("       ...\n");

        return new IllegalPostFileFormatException(postId, lineNumber, details.toString());
    }

    private String computeMd5(String body) throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("MD5");
        md.update(rawContent);
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toLowerCase();
    }
}
