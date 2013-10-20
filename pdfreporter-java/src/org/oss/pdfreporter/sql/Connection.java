/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.sql;

import java.io.IOException;
import java.sql.DriverManager;

import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.IPreparedStatement;
import org.oss.pdfreporter.sql.SQLException;



public class Connection implements IConnection {

	private final java.sql.Connection delegate;
	
	Connection(String driverClass, String connectionPrefix, String url, String user, String password) throws SQLException {
		super();
		try {
			Class.forName(driverClass);
			this.delegate = DriverManager.getConnection(connectionPrefix + url, user, password);
		} catch (ClassNotFoundException e) {
			throw new SQLException("Driver: " + driverClass + " not found.");
		} catch (java.sql.SQLException e) {
			throw new SQLException("Error connecting to " + url,e);
		}
	}


	@Override
	public IPreparedStatement prepareStatement(String query)
			throws SQLException {
		try {
			return new PreparedStatement(delegate.prepareStatement(query));
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void close() throws IOException {
		try {
			delegate.close();
		} catch (java.sql.SQLException e) {
			throw new IOException(e);
		}
	}
	

}
