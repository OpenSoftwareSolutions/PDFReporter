package org.oss.pdfreporter.sql;

import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.SQLException;
import org.oss.pdfreporter.sql.factory.AbstractSqlFactory;

public class SqlFactory extends AbstractSqlFactory {
	
	private final String driverClass;
	private final String connectionPrefix;

	private SqlFactory(String driverClass, String connectionPrefix) {
		super();
		this.driverClass = driverClass;
		this.connectionPrefix = connectionPrefix;
	}

	public static void registerFactory(String driverClass, String connectionPrefix) {
		ApiRegistry.register(new SqlFactory(driverClass,connectionPrefix));
	}
	
	@Override
	public IConnection newConnection(String url, String user,
			String password) throws SQLException {
		return new Connection(driverClass, connectionPrefix, url, user, password);
	}

}
