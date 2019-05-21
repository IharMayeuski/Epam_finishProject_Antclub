package by.epam.club.dao.pool;

import java.util.ResourceBundle;

public class DBResourceManager {
	public static final String DB_DRIVER = "db.driver";
	public static final String DB_URL = "db.url";
	public static final String DB_LOGIN = "db.login";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_POOL_SIZE = "db.poolsize";

	private final String PATH = "resource.db";

	private final static DBResourceManager instance = new DBResourceManager();
	private ResourceBundle jdbcProperties = ResourceBundle.getBundle(PATH);
	public static DBResourceManager getInstance() {
		return instance;
	}
	public String getValue(String key) {
		return jdbcProperties.getString(key);
	}

}