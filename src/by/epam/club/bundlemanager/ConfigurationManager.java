package by.epam.club.bundlemanager;

import java.util.ResourceBundle;

import static by.epam.club.entity.Parameter.RESOURCE_CONFIG;

public class ConfigurationManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_CONFIG);
    private ConfigurationManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
