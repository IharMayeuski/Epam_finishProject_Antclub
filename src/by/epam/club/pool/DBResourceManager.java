package by.epam.club.pool;

import java.util.ResourceBundle;

 class DBResourceManager {
	 static final String DB_DRIVER = "db.driver";
	 static final String DB_URL = "db.url";
	 static final String DB_LOGIN = "db.login";
	 static final String DB_PASSWORD = "db.password";
	 static final String DB_POOL_SIZE = "db.poolsize";

	private final String PATH = "resource.db";

	private final static DBResourceManager instance = new DBResourceManager();
	private ResourceBundle jdbcProperties = ResourceBundle.getBundle(PATH);
	 static DBResourceManager getInstance() {
		return instance;
	}
	 String getValue(String key) {
		return jdbcProperties.getString(key);
	}

}