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
