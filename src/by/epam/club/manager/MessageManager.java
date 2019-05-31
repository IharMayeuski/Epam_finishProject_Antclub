package by.epam.club.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static final String RESOURCE_MESSAGES  = "resource.messages";
    private final static ResourceBundle resourceBundleRU = ResourceBundle.getBundle(RESOURCE_MESSAGES, new Locale("rus", "BY"));
    private final static ResourceBundle resourceBundleEN = ResourceBundle.getBundle(RESOURCE_MESSAGES, new Locale("en", "UK"));

    private MessageManager() {
    }

    public static String getProperty(String key, String locale) {

        if (locale != null && locale.equals("rus")) {
            return resourceBundleRU.getString(key);
        } else {
            return resourceBundleEN.getString(key);
        }
    }
}