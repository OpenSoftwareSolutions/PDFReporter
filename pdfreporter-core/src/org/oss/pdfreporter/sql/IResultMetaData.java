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


/**
 * Metadata for a result set record.
 * @author donatmuller, 2013, last change 10:58:30 AM
 */
public interface IResultMetaData {
	/**
	 * Number of columns.
	 * All functions which take a columnIndex as parameter allow
	 * values in the range 1 - columnCount
	 * @return
	 * @throws SQLException
	 */
	int getColumnCount() throws SQLException;
	
	/**
	 * Returns the name of the column.
	 * @param columnIndex
	 * @return
	 */
	String getColumnName(int columnIndex) throws SQLException;

	/**
	 * Returns the label of the column.
	 * This is the alias specified with the AS in the select statement.
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	String getColumnLabel(int columnIndex) throws SQLException;
	
	/**
	 * Returns the data type returned by IResultSet.getObject().
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	String getColumnClassName(int columnIndex) throws SQLException;
	
	
	
	/**
	 * Returns the SQL datatype of the column.
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	SqlType getColumnType(int columnIndex) throws SQLException;
}
