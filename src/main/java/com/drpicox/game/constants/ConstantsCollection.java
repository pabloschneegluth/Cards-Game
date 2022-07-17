package com.drpicox.game.constants;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ConstantsCollection {

    private final String collectionName;

    public ConstantsCollection(String collectionName) {
        this.collectionName = collectionName;
    }

    private Map<String, Constants> constantsByName = new TreeMap<>();
    private Map<String, String> pathsByName = new TreeMap<>();

    public void add(Constants constants, String path) {
        var name = constants.getString("name");
        if (name == null) {
            throw new RuntimeException(
                "The property file for \"" +collectionName +
                    "\" with file name \"" + path + "\", " +
                    "does not have name.\n" +
                    "- constants collection name: \"" + collectionName + "\"\n" +
                    "- properties file path     : \"" + path + "\"\n" +
                    "- constants directory path : src/main/resources/" + collectionName + "\n" +
                    "Please, add the line name=TheCorrespondingName to the properties file."
            );
        }

        if (constantsByName.containsKey(name)) {
            throw new RuntimeException(
                "There is already a properties file in \"" +collectionName +
                    "\" with the constant name \"" + name + "\", " +
                    "the name is duplicated.\n" +
                    "- constants collection name          : \"" + collectionName + "\"\n" +
                    "- duplicated name                    : \"" + name + "\"\n" +
                    "- original properties file path      : \"" + pathsByName.get(name) + "\"\n" +
                    "- properties file path repeating name: \"" + path + "\"\n" +
                    "- constants directory path           : src/main/resources/" + collectionName + "\n" +
                    "Please, verify that both files have a different name."
            );
        }

        constantsByName.put(name, constants);
        pathsByName.put(name, path);
    }

    public Constants getByName(String constantsNameValue) {
        var constants = constantsByName.get(constantsNameValue);
        if (constants != null) return constants;

        throw new RuntimeException(
            "There is no properties file in resources for a \"" + collectionName + "\" with name \"" + constantsNameValue + "\".\n" +
                "- missing name             : \"" + constantsNameValue + "\"\n" +
                "- constants collection name: \"" + collectionName + "\"\n" +
                "- constants directory path : src/main/resources/" + collectionName + "\n" +
                "Please, verify that there is a .properties file that contains a line with name=" + constantsNameValue + " inside the corresponding directory."
        );
    }

}
