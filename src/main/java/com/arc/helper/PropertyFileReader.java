package com.arc.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyFileReader {
    private FileReader fileReader;
    private String path;

    public PropertyFileReader(String path) {
        this.path = path;
    }
    public PropertyFileReader() {
    }
    private Map<String, String> getConfigData;
    public String getData(String key){
        try {
//            fileReader = new FileReader("resources/testConfig.properties");
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Properties properties = new Properties();
        try {
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }
    public Map<String, String> getPropertyFileData(String path) {
        // Load the properties path "Start Time"
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(fileReader);
        } catch (IOException e) {

            e.printStackTrace();
        }
        getConfigData = new HashMap<String, String>();
        getConfigData.putAll(properties.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString())));
        return getConfigData;
    }
}
