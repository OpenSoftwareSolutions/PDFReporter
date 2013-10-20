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

import java.io.Closeable;
import java.math.BigDecimal;

/**
 * Prepared statement to execute a sql query.
 * @author donatmuller, 2013, last change 11:05:53 AM
 * 
 */
public interface IPreparedStatement extends Closeable {
	
	/**
	 * Returns the resultset for the query.
	 * @return
	 */
	IResultSet executeQuery() throws SQLException;
	
	void setBlob(int parameterIndex, IBlob value) throws SQLException;
	void setBoolean(int parameterIndex, boolean value) throws SQLException;
	void setByte(int parameterIndex, byte value) throws SQLException;
	void setDate(int parameterIndex, IDate value) throws SQLException;
	void setDateTime(int parameterIndex, IDateTime value) throws SQLException;
	void setDecimal(int parameterIndex, BigDecimal value) throws SQLException;
	void setDouble(int parameterIndex, double value) throws SQLException;
	void setFloat(int parameterIndex, float value) throws SQLException;
	void setInt(int parameterIndex, int value) throws SQLException;
	void setLong(int parameterIndex, long value) throws SQLException;
	void setObject(int parameterIndex, Object value) throws SQLException;
	void setShort(int parameterIndex, short value) throws SQLException;
	void setString(int parameterIndex, String value) throws SQLException;
	void setTime(int parameterIndex, ITime value) throws SQLException;
	void setTimestamp(int parameterIndex, ITimestamp value) throws SQLException;
	void setNull(int parameterIndex, SqlType type) throws SQLException;
	
		
	/**
	 * Cancels a long running query.
	 * @throws SQLException
	 */
	void cancel() throws SQLException;
}
