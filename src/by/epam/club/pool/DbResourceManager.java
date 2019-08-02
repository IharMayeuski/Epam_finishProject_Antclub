package by.epam.club.pool;

import java.util.ResourceBundle;
/**
 * The class for taking parameters for connection to Data Base MySQL
 *
 * @author Maeuski Igor
 * @version 1.0
 */

class DbResourceManager {
    static final String DB_DRIVER = "db.driver";
    static final String DB_URL = "db.url";
    static final String DB_LOGIN = "db.login";
    static final String DB_PASSWORD = "db.password";
    static final String DB_POOL_SIZE = "db.poolsize";
    private static final String PATH = "resource.db";
    private final static DbResourceManager instance = new DbResourceManager();
    private ResourceBundle jdbcProperties = ResourceBundle.getBundle(PATH);

    /**
     * @return this DbResourceManager
     */
    protected static DbResourceManager getInstance() {
        return instance;
    }

    /**
     * @param key for checking db.properties
     * @return param by key from db.properties
     */
    String getValue(String key) {
        return jdbcProperties.getString(key);
    }

}