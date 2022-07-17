package com.drpicox.game.blog;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.constants.ConstantsLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class AuthorsService {

    private final ConstantsLoader constantsLoader;

    public AuthorsService(ConstantsLoader constantsLoader) {
        this.constantsLoader = constantsLoader;
    }

    private Constants authors;

    private Constants getAuthors() throws IOException, URISyntaxException {
        if (authors == null) authors = readAuthorsFile();
        return authors;
    }

    private Constants readAuthorsFile() throws IOException, URISyntaxException {
        return constantsLoader.load( "authors.properties");
    }

    public boolean containsGitHubUser(String value) throws IOException, URISyntaxException {
        var authors = getAuthors();
        return authors.containsKey(value);
    }

    public Collection<String> getGitHubUsers() {
        return authors.keySet();
    }
}
