package org.oss.pdfreporter.android.sql;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.TreeMap;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.sql.IBlob;
import org.oss.pdfreporter.sql.IDate;
import org.oss.pdfreporter.sql.IDateTime;
import org.oss.pdfreporter.sql.IPreparedStatement;
import org.oss.pdfreporter.sql.IResultSet;
import org.oss.pdfreporter.sql.ITime;
import org.oss.pdfreporter.sql.ITimestamp;
import org.oss.pdfreporter.sql.SQLException;
import org.oss.pdfreporter.sql.SqlType;

import android.database.sqlite.SQLiteDatabase;

public class SQLitePreparedStatement implements IPreparedStatement{
	private TreeMap<Integer, String> values;
	private SQLiteDatabase db;
	private String sql;
	
	public SQLitePreparedStatement(String sql, SQLiteDatabase db) {
		this.db = db;
		values = new TreeMap<Integer, String>();
		this.sql = sql;
	}
	
	@Override
	public void close() throws IOException {
	}

	@Override
	public IResultSet executeQuery() throws SQLException {
		Collection<String> strings = values.values();
		String[] array = strings.toArray(new String[strings.size()]);
		return new SQLiteResultSet(db.rawQuery(sql, array));
	}

	@Override
	public void setBlob(int parameterIndex, IBlob value) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
	public void setBoolean(int parameterIndex, boolean value) throws SQLException {
		values.put(new Integer(parameterIndex), value?"1":"0");
	}

	@Override
	public void setByte(int parameterIndex, byte value) throws SQLException {
		values.put(new Integer(parameterIndex), String.valueOf(value));		
	}

	@Override
	public void setDate(int parameterIndex, IDate value) throws SQLException {
		String text = String.format("%.4d-%.2d-%.2d 00:00:00.000", value.getYear(), value.getMonth(), value.getDay());
		values.put(new Integer(parameterIndex), text);	
	}

	@Override
	public void setDateTime(int parameterIndex, IDateTime value) throws SQLException {
		String text = String.format("%.4d-%.2d-%.2d %.2d:%.2d:%.2d.000", value.getYear(), value.getMonth(), value.getDay(), value.getHours(), value.getMinutes(), value.getSeconds());
		values.put(new Integer(parameterIndex), text);	
	}

	@Override
	public void setDecimal(int parameterIndex, BigDecimal value) throws SQLException {
		values.put(new Integer(parameterIndex), String.valueOf(value.doubleValue()));	
	}

	@Override
	public void setDouble(int parameterIndex, double value) throws SQLException {
		values.put(new Integer(parameterIndex), String.valueOf(value));	
	}

	@Override
	public void setFloat(int parameterIndex, float value) throws SQLException {
		values.put(new Integer(parameterIndex), String.valueOf(value));	
	}

	@Override
	public void setInt(int parameterIndex, int value) throws SQLException {
		values.put(new Integer(parameterIndex), String.valueOf(value));	
	}

	@Override
	public void setLong(int parameterIndex, long value) throws SQLException {
		values.put(new Integer(parameterIndex), String.valueOf(value));	
	}

	@Override
	public void setObject(int parameterIndex, Object value) throws SQLException {
		throw new NotImplementedException();		
	}

	@Override
	public void setShort(int parameterIndex, short value) throws SQLException {
		values.put(new Integer(parameterIndex), String.valueOf(value));	
	}

	@Override
	public void setString(int parameterIndex, String value) throws SQLException {
		values.put(new Integer(parameterIndex), value);	
	}

	@Override
	public void setTime(int parameterIndex, ITime value) throws SQLException {
		String text = String.format("%.4d-%.2d-%.2d %.2d:%.2d:%.2d.000", 0, 1, 1, value.getHours(), value.getMinutes(), value.getSeconds());
		values.put(new Integer(parameterIndex), text);
	}

	@Override
	public void setTimestamp(int parameterIndex, ITimestamp value) throws SQLException {
		values.put(new Integer(parameterIndex), String.valueOf(value.getMilliseconds()));	
	}

	@Override
	public void setNull(int parameterIndex, SqlType type) throws SQLException {
		values.put(new Integer(parameterIndex), "NULL");	
	}

	@Override
	public void cancel() throws SQLException {		
	}

}
