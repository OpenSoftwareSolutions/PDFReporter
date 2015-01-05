package org.oss.pdfreporter.android.sql;

import org.oss.pdfreporter.sql.IResultMetaData;
import org.oss.pdfreporter.sql.SQLException;
import org.oss.pdfreporter.sql.SqlType;

import android.database.Cursor;

public class SQLiteMetaData implements IResultMetaData {

	private Cursor cursor;
	
	public SQLiteMetaData(Cursor cursor) {
		this.cursor = cursor;
	}
	
	@Override
	public int getColumnCount() throws SQLException {
		return cursor.getColumnCount();
	}

	@Override
	public String getColumnName(int columnIndex) throws SQLException {
		return cursor.getColumnName(columnIndex-1);
	}

	@Override
	public String getColumnLabel(int columnIndex) throws SQLException {
		return cursor.getColumnName(columnIndex);
	}

	@Override
	public String getColumnClassName(int columnIndex) throws SQLException {
		int type = cursor.getType(columnIndex-1);
		switch(type) {
		case Cursor.FIELD_TYPE_NULL: return "NULL";
		case Cursor.FIELD_TYPE_BLOB: return "BLOB";
		case Cursor.FIELD_TYPE_FLOAT: return "FLOAT";
		case Cursor.FIELD_TYPE_INTEGER: return "INTEGER";
		case Cursor.FIELD_TYPE_STRING: return "TEXT";
		default: return null;
		}
	}

	@Override
	public SqlType getColumnType(int columnIndex) throws SQLException {
		int type = cursor.getType(columnIndex-1);
		switch(type) {
		case Cursor.FIELD_TYPE_NULL: return SqlType.VARCHAR;
		case Cursor.FIELD_TYPE_BLOB: return SqlType.BLOB;
		case Cursor.FIELD_TYPE_FLOAT: return SqlType.FLOAT;
		case Cursor.FIELD_TYPE_INTEGER: return SqlType.INTEGER;
		case Cursor.FIELD_TYPE_STRING: return SqlType.VARCHAR;
		default: return null;
		}
	}

}
