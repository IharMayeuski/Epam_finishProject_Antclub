package resource;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("resource.messages");
    private MessageManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
