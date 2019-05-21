package by.epam.club.dao.pool_old;

import by.epam.club.dao.pool.DBResourceManager;
import by.epam.club.exception.ConnectionPoolException;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

import static by.epam.club.dao.pool.DBResourceManager.*;


public final class ConnectionPoolDecorator {
	private static final ConnectionPoolDecorator instance = new ConnectionPoolDecorator();
	public static ConnectionPoolDecorator getInstance() {
		return instance;
	}	

	private BlockingQueue <Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;
	private String driverName;
	private String url;
	private String login;
	private String password;
	private int poolSize;

	private ConnectionPoolDecorator() {
		DBResourceManager dbResourceManager = DBResourceManager.getInstance();
		this.driverName = dbResourceManager.getValue(DB_DRIVER);
		this.url = dbResourceManager.getValue(DB_URL);
		this.login = dbResourceManager.getValue(DB_LOGIN);
		this.password = dbResourceManager.getValue(DB_PASSWORD);
		
		this.poolSize = Integer.parseInt(dbResourceManager.getValue(DB_POOL_SIZE));
		try {
			initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}		
	}

	public void initPoolData() throws ConnectionPoolException {
		try {
			Class.forName(driverName);
			
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
			givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
			
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, login, password);
				PooledConnection pooledConnection = new PooledConnection(connection);
				connectionQueue.add(pooledConnection);				
			}
			
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Where is your driver?", e);
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in Connection pool", e);
		}		
	}

	public void dispose () {
		clearConnectionQueue();
	}
	
	private void clearConnectionQueue() {
		try {
			closeConnestionsQueue(givenAwayConQueue);
			closeConnestionsQueue(connectionQueue);
		} catch (SQLException e) {
			// logger.log
		}		
	}
	
	public Connection takeConection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("something wrong", e);
		}
		return connection;		
	}
	
	public void closeConnection (ResultSet rs, PreparedStatement st, Connection con) {
		try {
			if (rs != null) {			
					rs.close();
				}
			} catch (SQLException e) {
				//logger.log	
			}
		try {
			if (st != null) {			
					st.close();
				}
			} catch (SQLException e) {
				//logger.log	
			}
		try {
			if (con != null) {			
					con.close();
				}
			} catch (SQLException e) {
				//logger.log	
			}			
		}
	
	public void closeConnection (PreparedStatement st, Connection con) {
		try {
			if (st != null) {			
					st.close();
				}
			} catch (SQLException e) {
				//logger.log	
			}
		try {
			if (con != null) {			
					con.close();
				}
			} catch (SQLException e) {
				//logger.log	
			}			
		}
	
	private void closeConnestionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}
	
	//Pattern decorator
	private class PooledConnection implements Connection {
		
		private Connection connection;		

		public PooledConnection(Connection c) throws SQLException {
			
			this.connection = c;
			this.connection.setAutoCommit(true);
		}
		
		public void reallyClose() throws SQLException {
			connection.close();
		}
		
		public void clearWarnings() throws SQLException {
			connection.clearWarnings();			
		}
		
		public void close() throws SQLException {
			if (connection.isClosed()) {
				throw new SQLException("something wrong");
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
			if (!givenAwayConQueue.remove(this)) {
				throw new SQLException("something wrong");
			}
			if (!connectionQueue.offer(this)) {
				throw new SQLException("something wrong");
			}
		}
		
		public void commit() throws SQLException {
			connection.commit();			
		}

		

		public boolean isWrapperFor(Class<?> arg0) throws SQLException {
			return connection.isWrapperFor(arg0);
		}

		public <T> T unwrap(Class<T> arg0) throws SQLException {
			return connection.unwrap(arg0);
		}

		public void abort(Executor executor) throws SQLException {
			connection.abort(executor);			
		}
		
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return connection.createArrayOf(typeName, elements);
		}

		public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

		public Clob createClob() throws SQLException {
			return connection.createClob();
		}

		public NClob createNClob() throws SQLException {
			return connection.createNClob();
		}

		public SQLXML createSQLXML() throws SQLException {
			return connection.createSQLXML();
		}

		public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			return connection.createStruct(typeName, attributes);
		}

		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

		public String getClientInfo(String name) throws SQLException {
			return connection.getClientInfo(name);
		}

		public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

		public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

		public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}

		public String getSchema() throws SQLException {
			return connection.getSchema();
		}

		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		public boolean isValid(int timeout) throws SQLException {
			return connection.isValid(timeout);
		}

		public String nativeSQL(String sql) throws SQLException {
			return connection.nativeSQL(sql);
		}

		public CallableStatement prepareCall(String sql) throws SQLException {
			return connection.prepareCall(sql);
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return connection.prepareStatement(sql);
		}

		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			return connection.prepareStatement(sql, columnIndexes);
		}

		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			return connection.prepareStatement(sql, columnNames);
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			connection.releaseSavepoint(savepoint);		
		}

		public void rollback() throws SQLException {
			connection.rollback();			
		}

		public void rollback(Savepoint savepoint) throws SQLException {
			connection.rollback(savepoint);			
		}

		public void setAutoCommit(boolean autoCommit) throws SQLException {
			connection.setAutoCommit(autoCommit);		
		}

		public void setCatalog(String catalog) throws SQLException {
			connection.setCatalog(catalog);			
		}

		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			connection.setClientInfo(properties);			
		}

		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			connection.setClientInfo(name, value);
		}

		public void setHoldability(int holdability) throws SQLException {
			connection.setHoldability(holdability);
		}

		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			connection.setNetworkTimeout(executor, milliseconds);
		}

		public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);			
		}

		public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

		public Savepoint setSavepoint(String name) throws SQLException {
			return connection.setSavepoint(name);
		}

		public void setSchema(String schema) throws SQLException {
			connection.setSchema(schema);
		}

		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);
		}

		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			connection.setTypeMap(map);
		}
		
		
		
	}
}
