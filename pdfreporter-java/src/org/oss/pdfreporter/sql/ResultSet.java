package org.oss.pdfreporter.sql;

import java.io.IOException;
import java.math.BigDecimal;

import org.oss.pdfreporter.sql.IBlob;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.IDate;
import org.oss.pdfreporter.sql.IDateTime;
import org.oss.pdfreporter.sql.IResultMetaData;
import org.oss.pdfreporter.sql.IResultSet;
import org.oss.pdfreporter.sql.ITime;
import org.oss.pdfreporter.sql.ITimestamp;
import org.oss.pdfreporter.sql.SQLException;
import org.oss.pdfreporter.sql.factory.AbstractSqlFactory;
import org.oss.pdfreporter.sql.factory.ISqlFactory;


public class ResultSet implements IResultSet {

	private final static ISqlFactory TYPE_FACTORY = new TypeFactory();
	
	private final java.sql.ResultSet delegate;
	
	ResultSet(java.sql.ResultSet delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public boolean next() throws SQLException {
		try {
			return delegate.next();
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		try {
			return delegate.getBoolean(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public byte getByte(int columnIndex) throws SQLException {
		try {
			return delegate.getByte(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public double getDouble(int columnIndex) throws SQLException {
		try {
			return delegate.getDouble(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public float getFloat(int columnIndex) throws SQLException {
		try {
			return delegate.getFloat(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public int getInt(int columnIndex) throws SQLException {
		try {
			return delegate.getInt(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public long getLong(int columnIndex) throws SQLException {
		try {
			return delegate.getLong(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public short getShort(int columnIndex) throws SQLException {
		try {
			return delegate.getShort(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public String getString(int columnIndex) throws SQLException {
		try {
			return delegate.getString(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public BigDecimal getDecimal(int columnIndex) throws SQLException {
		try {
			return delegate.getBigDecimal(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public IDate getDate(int columnIndex) throws SQLException {
		try {
			return TYPE_FACTORY.newDate(delegate.getDate(columnIndex));
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public ITimestamp getTimestamp(int columnIndex) throws SQLException {
		try {
			return TYPE_FACTORY.newTimestamp(delegate.getTimestamp(columnIndex).getTime());
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public ITime getTime(int columnIndex) throws SQLException {
		try {
			return TYPE_FACTORY.newTime(delegate.getTime(columnIndex));
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public IDateTime getDateTime(int columnIndex) throws SQLException {
		try {
			return TYPE_FACTORY.newDateTime(delegate.getDate(columnIndex));
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public IBlob getBlob(int columnIndex) throws SQLException {
		try {
			return TYPE_FACTORY.newBlob(delegate.getBlob(columnIndex).getBinaryStream());
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		try {
			return delegate.getObject(columnIndex);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public IResultMetaData getMetaData() throws SQLException {
		try {
			return new ResultMetaData(delegate.getMetaData());
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
	
	@Override
	public boolean wasNull() throws SQLException {
		try {
			return delegate.wasNull();
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}
	
	/**
	 * Helper class to create data types.
	 * @author donatmuller, 2013, last change 2:54:11 AM
	 * 
	 */
	private static class TypeFactory extends AbstractSqlFactory {
		@Override
		public IConnection newConnection(String parameter, String user,
				String password) throws SQLException {
			return null;
		}
	}



	
}
