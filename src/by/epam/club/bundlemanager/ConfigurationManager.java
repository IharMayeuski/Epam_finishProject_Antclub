package by.epam.club.bundlemanager;

import java.util.ResourceBundle;

import static by.epam.club.entity.Parameter.RESOURCE_CONFIG;

/**
 * The class is created for receiving paths and send it to .jsp pages
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class ConfigurationManager {

    private final static ResourceBundle RESOURCE = ResourceBundle.getBundle(RESOURCE_CONFIG);

    /**
     * @param key for receiving path of .jsp
     * @return get path of the page from bundle
     */
    public static String getProperty(String key) {
        return RESOURCE.getString(key);
    }
}
