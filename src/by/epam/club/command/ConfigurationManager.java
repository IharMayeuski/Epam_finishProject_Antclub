package by.epam.club.command;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
    private ConfigurationManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
