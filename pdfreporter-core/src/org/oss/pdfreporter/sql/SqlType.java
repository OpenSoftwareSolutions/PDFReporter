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

/**
 * SQL datatypes
 * @author donatmuller, 2013, last change 5:12:27 PM
 * 
 */
public enum SqlType {
	TINYINT, // 1 byte
	SMALLINT, // 2 bytes
	MEDIUMINT, // 3 bytes
	INTEGER, // 4 bytes
	BIGINT, // 8 bytes
	FLOAT, // 4 bytes
	DOUBLE, // 8 bytes
	DECIMAL, // depends on precision
	NUMERIC, // depends on precision
	CHAR, // depends on size
	VARCHAR, // depends on data	
	DATE, // depends on implementation
	TIME, // depends on implementation
	TIMESTAMP, // depends on implementation
	DATETIME, // depends on implementation
	BLOB, // depends on data
}
