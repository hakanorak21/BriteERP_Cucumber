package com.iteam.brite_erp.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    //This class will be responsible for loading properties file and
    //will provide access to values based on key names

    //We used this class to load custom .properties files
    private static Properties configFile;

    static{
        try {
            //provides access to file
            //try-catch block stands for handling exception
            //if exception occurs, code inside catch block will be executed
            //any class that's related to I/O produces checked exceptions
            //without handling checked exception, you cannot run a code
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            configFile = new Properties();

            //load your properties file
            configFile.load(input);

        } catch (IOException e) {
            System.out.println("File was not loaded :(");
            e.printStackTrace();
        }

    }

    public static String getProperty(String key){
        return configFile.getProperty(key);
    }

}