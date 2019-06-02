package by.epam.club.bundlemanager;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static String RESOURCE_CONFIG = "resource.config";

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_CONFIG);
    private ConfigurationManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
