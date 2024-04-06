package com.seleniumapi.utils.PropertyReaderUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final PropertyReader instance = new PropertyReader();
    private static final Properties properties = new Properties();
    private PropertyReader(){

    }

    public static PropertyReader getInstance(){
        return instance;
    }


    public  String getProperty(String filePath, String key) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        properties.load(fis);
        return properties.getProperty(key);


    }
}
