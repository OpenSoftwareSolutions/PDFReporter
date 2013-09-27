package org.oss.pdfreporter.sql;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Types;

import org.oss.pdfreporter.exception.NotImplementedException;


public class PreparedStatement implements IPreparedStatement {

	private final java.sql.PreparedStatement delegate;
	
	
	PreparedStatement(java.sql.PreparedStatement delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public IResultSet executeQuery() throws SQLException {
		try {
			return new ResultSet(delegate.executeQuery());
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setBlob(int parameterIndex, IBlob value) throws SQLException {
		try {
			delegate.setBlob(parameterIndex, value.getInputStream());
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setBoolean(int parameterIndex, boolean value)
			throws SQLException {
		try {
			delegate.setBoolean(parameterIndex, value);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}		
	}

	@Override
	public void setByte(int parameterIndex, byte value) throws SQLException {
		try {
			delegate.setByte(parameterIndex, value);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	
	@Override
	public void setDate(int parameterIndex, IDate value) throws SQLException {
		try {
			delegate.setDate(parameterIndex,toDate(value.getDate()));
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setDateTime(int parameterIndex, IDateTime value)
			throws SQLException {
		try {
			delegate.setDate(parameterIndex,toDate(value.getDate()));
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setDecimal(int parameterIndex, BigDecimal value)
			throws SQLException {
		try {
			delegate.setBigDecimal(parameterIndex, value);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setDouble(int parameterIndex, double value) throws SQLException {
		try {
			delegate.setDouble(parameterIndex, value);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setFloat(int parameterIndex, float value) throws SQLException {
		try {
			delegate.setFloat(parameterIndex, value);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setInt(int parameterIndex, int value) throws SQLException {
		try {
			delegate.setInt(parameterIndex, value);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setLong(int parameterIndex, long value) throws SQLException {
		try {
			delegate.setLong(parameterIndex, value);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setObject(int parameterIndex, Object value) throws SQLException {
		throw new NotImplementedException();
		
	}

	@Override
	public void setShort(int parameterIndex, short value) throws SQLException {
		try {
			delegate.setShort(parameterIndex, value);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setString(int parameterIndex, String value) throws SQLException {
		try {
			delegate.setString(parameterIndex, value);
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setTime(int parameterIndex, ITime value) throws SQLException {
		try {
			delegate.setTime(parameterIndex, toTime(value.getDate()));
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setTimestamp(int parameterIndex, ITimestamp value)
			throws SQLException {
		try {
			delegate.setTimestamp(parameterIndex, toTimestamp(value.getMilliseconds()));
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setNull(int parameterIndex, SqlType type) throws SQLException {
		try {
			delegate.setNull(parameterIndex, toSqlType(type));
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
	public void cancel() throws SQLException {
		try {
			delegate.cancel();
		} catch (java.sql.SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private static java.sql.Date toDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	private static java.sql.Time toTime(java.util.Date date) {
		return new java.sql.Time(date.getTime());
	}
	
	private static java.sql.Timestamp toTimestamp(long milliseconds) {
		return new java.sql.Timestamp(milliseconds);
	}
	
	private static int toSqlType(SqlType type) throws SQLException {
		switch(type) {
		case BIGINT:
			return Types.BIGINT;
		case CHAR:
			return Types.CHAR;
		case DATE:
			return Types.DATE;
		case DATETIME:
			return Types.DATE;
		case DECIMAL:
			return Types.DECIMAL;
		case DOUBLE:
			return Types.DOUBLE;
		case FLOAT:
			return Types.FLOAT;
		case INTEGER:
			return Types.INTEGER;
		case MEDIUMINT:
			return Types.INTEGER;
		case SMALLINT:
			return Types.SMALLINT;
		case NUMERIC:
			return Types.NUMERIC;
		case TIME:
			return Types.TIME;
		case TIMESTAMP:
			return Types.TIMESTAMP;
		case TINYINT:
			return Types.TINYINT;
		case VARCHAR:
			return Types.VARCHAR;
			default:
				throw new SQLException("Type: " + type + " cannot be set to null");
		}
	}

}
