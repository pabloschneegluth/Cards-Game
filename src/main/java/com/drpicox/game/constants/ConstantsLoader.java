package com.drpicox.game.constants;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class ConstantsLoader {

    private final Map<String, Constants> constantsCache = new HashMap<>();

    public ConstantsCollection loadCollection(String collectionName) throws URISyntaxException, IOException {
        var collection = new ConstantsCollection(collectionName);
        var directoryUri = getUri(collectionName);
        var directory = new File(directoryUri);
        loadDirectory(collection, directory);
        return collection;
    }

    public Constants load(String resourceName) throws IOException, URISyntaxException {
        if (!constantsCache.containsKey(resourceName)) {
            var uri = getUri(resourceName);
            var file = new File(uri);
            constantsCache.put(resourceName, loadFile(file));
        }

        return constantsCache.get(resourceName);
    }

    private void loadDirectory(ConstantsCollection collection, File directory) throws IOException {
        var files = directory.listFiles();
        for (var file: files) {
            if (file.isDirectory()) loadDirectory(collection, file);
            else loadFile(collection, file);
        }
    }

    private void loadFile(ConstantsCollection collection, File file) throws IOException {
        var constants = loadFile(file);
        collection.add(constants, file.getPath());
    }

    private static Constants loadFile(File file) throws IOException {
        return Constants.load(file);
    }

    private static URI getUri(String resourceName) throws URISyntaxException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        var uri = loader.getResource(resourceName).toURI();
        return uri;
    }
}
