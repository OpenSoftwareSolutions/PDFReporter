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
package org.oss.pdfreporter.sql.factory;

import java.io.InputStream;
import java.util.Date;

import org.oss.pdfreporter.sql.IBlob;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.IDate;
import org.oss.pdfreporter.sql.IDateTime;
import org.oss.pdfreporter.sql.ITime;
import org.oss.pdfreporter.sql.ITimestamp;
import org.oss.pdfreporter.sql.SQLException;


/**
 * Sql connectivity factory.
 * The interface supports queries with prepared statements.
 * No DDL or modifying operations are supported, neither transactions or cursors.
 * @author donatmuller, 2013, last change 5:39:58 PM
 */
public interface ISqlFactory {
	/**
	 * Returns a connection for the connection parameter.
	 * It is up to the implementor to define the syntax of the connection parameter.<br>
	 * The parameter could include a driver class name or just an url and assume that a driver
	 * was already loaded.
	 * @param url
	 * @param user
	 * @param password
	 * @return
	 */
	IConnection newConnection(String url, String user, String password) throws SQLException;


	/**
	 * used by dynamic loading of specific database driver and JDBC-URL, when the implementation don't know about the specific driver-implementation.
	 * @param jdbcUrl
	 * @param user
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	IConnection createConnection(String jdbcUrl, String user, String password) throws SQLException;

	/**
	 * Creates a Date.
	 * @param date
	 * @return
	 */
	IDate newDate(Date date);

	/**
	 * Creates a Time.
	 * @param time
	 * @return
	 */
	ITime newTime(Date time);

	/**
	 * Creates a Timestamp.
	 * @param timestamp
	 * @return
	 */
	ITimestamp newTimestamp(long milliseconds);

	/**
	 * Creates a DateTime.
	 * @param datetime
	 * @return
	 */
	IDateTime newDateTime(Date datetime);

	/**
	 * Creates a Blob.
	 * @param is
	 * @return
	 */
	IBlob newBlob(InputStream is);

	/**
	 * Creates a Blob.
	 * @param bytes
	 * @return
	 */
	IBlob newBlob(byte[] bytes);
}
