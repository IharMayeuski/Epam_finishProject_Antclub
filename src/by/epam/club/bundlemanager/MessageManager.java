package by.epam.club.bundlemanager;

import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.club.entity.Parameter.EN_PARAM;
import static by.epam.club.entity.Parameter.RESOURCE_MESSAGES;

/**
 * The class for using internationalization on .jsp page and in the case of any exception
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class MessageManager {
    private static final String RESOURCE_MESSAGE = RESOURCE_MESSAGES;
    private final static ResourceBundle resourceBundleRU = ResourceBundle.getBundle(RESOURCE_MESSAGE, new Locale("rus", "BY"));
    private final static ResourceBundle resourceBundleEN = ResourceBundle.getBundle(RESOURCE_MESSAGE, new Locale("en", "UK"));

    /**
     * @param key for translating our message
     * @param locale for name of locale
     * @return text on the language by flag locale
     */
    public static String getProperty(String key, String locale) {
        if (locale != null && locale.equals(EN_PARAM)) {
            return resourceBundleEN.getString(key);
        } else {
            return resourceBundleRU.getString(key);
        }
    }
}