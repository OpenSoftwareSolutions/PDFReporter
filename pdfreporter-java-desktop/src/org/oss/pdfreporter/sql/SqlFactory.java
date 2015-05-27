/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
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

	private SqlFactory(String driverClass) {
		super();
		this.driverClass = driverClass;
		this.connectionPrefix = null;
	}

	public static void registerFactory(String driverClass, String connectionPrefix) {
		ApiRegistry.register(new SqlFactory(driverClass,connectionPrefix));
	}

	public static void registerFactory(String driverClass) {
		ApiRegistry.register(new SqlFactory(driverClass));
	}

	/**
	 * only for desktop testing
	 */
	public static void registerFactory() {
		registerFactory("org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/iva");
	}

	@Override
	public IConnection newConnection(String url, String user,
			String password) throws SQLException {
		return new Connection(driverClass, connectionPrefix, url, user, password);
	}

	/**
	 *only used with registerFactory(String driverClass);
	 *used by dynamic loading of specific database driver and JDBC-URL, when the implementation don't know about the specific driver-implementation.
	 *
	 * @param url
	 * @param user
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	@Override
	public IConnection createConnection(String url, String user,
			String password) throws SQLException {
		return new Connection(driverClass, url, user, password);
	}

}
