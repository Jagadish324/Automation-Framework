//package com.arc.frameworkDevice.utility;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Properties;
//
//public class PropertyFileReader {
//    private FileReader fileReader;
//    public String getData(String key){
//        CONSTANT.PROPERTY_CONFIG_PATH = "resources/testConfig.properties";
//        try {
//            fileReader = new FileReader(CONSTANT.PROPERTY_CONFIG_PATH);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        Properties properties = new Properties();
//        try {
//            properties.load(fileReader);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return properties.getProperty(key);
//    }
//}
