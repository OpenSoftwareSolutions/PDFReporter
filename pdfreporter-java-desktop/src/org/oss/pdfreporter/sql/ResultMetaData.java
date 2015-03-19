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

import java.sql.ResultSetMetaData;
import java.sql.Types;

import org.oss.pdfreporter.sql.IResultMetaData;
import org.oss.pdfreporter.sql.SQLException;
import org.oss.pdfreporter.sql.SqlType;

public class ResultMetaData implements IResultMetaData {

	private final java.sql.ResultSetMetaData delegate;
	
	
	ResultMetaData(ResultSetMetaData delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public int getColumnCount() throws SQLException {
		try {
			return delegate.getColumnCount();
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public String getColumnName(int columnIndex) throws SQLException {
		try {
			return delegate.getColumnName(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	
	@Override
	public String getColumnLabel(int columnIndex) throws SQLException {
		try {
			return delegate.getColumnLabel(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public String getColumnClassName(int columnIndex) throws SQLException {
		try {
			return delegate.getColumnClassName(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public SqlType getColumnType(int columnIndex) throws SQLException {
		try {
			return toSqlType(delegate.getColumnType(columnIndex));
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private static SqlType toSqlType(int type) throws SQLException {
		switch(type) {
		case Types.BIGINT:
			return SqlType.BIGINT;
		case Types.CHAR:
			return SqlType.CHAR;
		case Types.DATE:
			return SqlType.DATE;
		case Types.DECIMAL:
			return SqlType.DECIMAL;
		case Types.DOUBLE:
			return SqlType.DOUBLE;
		case Types.FLOAT:
			return SqlType.FLOAT;
		case Types.INTEGER:
			return SqlType.INTEGER;
		case Types.SMALLINT:
			return SqlType.SMALLINT;
		case Types.NUMERIC:
			return SqlType.NUMERIC;
		case Types.TIME:
			return SqlType.TIME;
		case Types.TIMESTAMP:
			return SqlType.TIMESTAMP;
		case Types.TINYINT:
			return SqlType.TINYINT;
		case Types.VARCHAR:
			return SqlType.VARCHAR;
		default:
			throw new SQLException("Type: " + type + " cannot be set to null");
		}
	}

	
}
