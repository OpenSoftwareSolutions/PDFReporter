/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oss.pdfreporter.engine.query;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JRDataset;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRParameter;
import org.oss.pdfreporter.engine.JRPropertiesHolder;
import org.oss.pdfreporter.engine.JRResultSetDataSource;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JRValueParameter;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.IDate;
import org.oss.pdfreporter.sql.IPreparedStatement;
import org.oss.pdfreporter.sql.IResultSet;
import org.oss.pdfreporter.sql.ITime;
import org.oss.pdfreporter.sql.ITimestamp;
import org.oss.pdfreporter.sql.SQLException;
import org.oss.pdfreporter.sql.SqlType;




/**
 * JDBC query executer for SQL queries.
 * <p/>
 * This query executer implementation offers built-in support for SQL queries.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRJdbcQueryExecuter.java 5707 2012-10-03 15:27:17Z lucianc $
 */
public class JRJdbcQueryExecuter extends JRAbstractQueryExecuter
{
	private static final Logger logger = Logger.getLogger(JRJdbcQueryExecuter.class.getName());

	public static final String CANONICAL_LANGUAGE = "SQL";
	
	protected static final String CLAUSE_ID_IN = "IN";
	protected static final String CLAUSE_ID_NOTIN = "NOTIN";
	
	public static final String CLAUSE_ID_EQUAL = "EQUAL";
	public static final String CLAUSE_ID_NOTEQUAL = "NOTEQUAL";
	
	public static final String CLAUSE_ID_LESS = "LESS";
	public static final String CLAUSE_ID_GREATER = "GREATER";
	public static final String CLAUSE_ID_LESS_OR_EQUAL = "LESS]";
	public static final String CLAUSE_ID_GREATER_OR_EQUAL = "[GREATER";
	
	public static final String CLAUSE_ID_BETWEEN = "BETWEEN";
	public static final String CLAUSE_ID_BETWEEN_CLOSED = "[BETWEEN]";
	public static final String CLAUSE_ID_BETWEEN_LEFT_CLOSED = "[BETWEEN";
	public static final String CLAUSE_ID_BETWEEN_RIGHT_CLOSED = "BETWEEN]";
	
	protected static final String TYPE_FORWARD_ONLY = "forwardOnly";
	protected static final String TYPE_SCROLL_INSENSITIVE = "scrollInsensitive";
	protected static final String TYPE_SCROLL_SENSITIVE = "scrollSensitive";
	
	protected static final String CONCUR_READ_ONLY = "readOnly";
	protected static final String CONCUR_UPDATABLE = "updatable";
	
	protected static final String HOLD_CURSORS_OVER_COMMIT = "hold";
	protected static final String CLOSE_CURSORS_AT_COMMIT = "close";
	
	protected static final String CACHED_ROWSET_CLASS = "com.sun.rowset.CachedRowSetImpl";

	protected IConnection connection;
	
	/**
	 * The statement used to fire the query.
	 */
	protected IPreparedStatement statement;

	protected IResultSet resultSet;
	
	// TODO (20.06.2013, Donat, Digireport): No cached row set support
	private boolean isCachedRowSet;

	private TimeZone timeZone;
	private boolean timeZoneOverride;
	
	/**
	 * 
	 */
	public JRJdbcQueryExecuter(
		JasperReportsContext jasperReportsContext, 
		JRDataset dataset, 
		Map<String,? extends JRValueParameter> parameters
		)
	{
		super(jasperReportsContext, dataset, parameters);
		
		connection = (IConnection) getParameterValue(JRParameter.REPORT_CONNECTION);
		if (connection == null)
		{
			if (logger.isLoggable(Level.WARNING))
			{
				logger.warning("The supplied java.sql.Connection object is null.");
			}
		}
		
		isCachedRowSet = getBooleanParameterOrProperty(JRJdbcQueryExecuterFactory.PROPERTY_CACHED_ROWSET, false);
		
		setTimeZone();

		registerFunctions();
		
		parseQuery();		
	}

	/**
	 * @deprecated Replaced by {@link #JRJdbcQueryExecuter(JasperReportsContext, JRDataset, Map)}.
	 */
	public JRJdbcQueryExecuter(JRDataset dataset, Map<String,? extends JRValueParameter> parameters)
	{
		this(DefaultJasperReportsContext.getInstance(), dataset, parameters);
	}

	
	/**
	 * Registers built-in {@link JRClauseFunction clause functions}.
	 * @see #registerFunctions()
	 * @see #appendClauseChunk(StringBuffer, String[])
	 */
	protected void registerFunctions()
	{
		// keeping empty for backwards compatibility, the functions are now regustered
		// as extensions by JDBCQueryClauseFunctionsExtensions
	}

	@Override
	protected String getCanonicalQueryLanguage()
	{
		return CANONICAL_LANGUAGE;
	}

	protected void setTimeZone()
	{
		String timezoneId = (String) getParameterValue(JRJdbcQueryExecuterFactory.PROPERTY_TIME_ZONE, true);
		if (timezoneId != null)
		{
			timeZoneOverride = true;
		}
		else
		{
			timezoneId = getPropertiesUtil().getProperty(dataset, JRJdbcQueryExecuterFactory.PROPERTY_TIME_ZONE);
		}
		timeZone = timezoneId == null || timezoneId.length() == 0 ? null : TimeZone.getTimeZone(timezoneId);
	}


	protected String getParameterReplacement(String parameterName)
	{
		return "?";
	}

	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.util.JRQueryExecuter#createDatasource()
	 */
	public JRDataSource createDatasource() throws JRException
	{
		JRResultSetDataSource dataSource = null;
		
		createStatement();
		
		if (statement != null)
		{
			try
			{
				// NOTICE (20.06.2013, Donat, Digireport): No cached row set support
				resultSet = statement.executeQuery();
				dataSource = new JRResultSetDataSource(getJasperReportsContext(), resultSet);
				dataSource.setTimeZone(timeZone, timeZoneOverride);
			}
			catch (SQLException e)
			{
				throw new JRException("Error executing SQL statement for : " + dataset.getName(), e);
			}
		}
		
		return dataSource;
	}
	
	
	protected void createStatement() throws JRException
	{
		String queryString = getQueryString();
		
		if (logger.isLoggable(Level.FINE))
		{
			logger.fine("SQL query string: " + queryString);
		}
		
		if (connection != null && queryString != null && queryString.trim().length() > 0)
		{
			try
			{
				// TODO (20.06.2013, Donat, Digireport): No cached result set type, concurrency, holdability, fetch size and max field size, max row size support
//				String type = getPropertiesUtil().getProperty(dataset,	JRJdbcQueryExecuterFactory.PROPERTY_JDBC_RESULT_SET_TYPE);
//				String concurrency = getPropertiesUtil().getProperty(dataset, JRJdbcQueryExecuterFactory.PROPERTY_JDBC_CONCURRENCY);
//				String holdability = getPropertiesUtil().getProperty(dataset, JRJdbcQueryExecuterFactory.PROPERTY_JDBC_HOLDABILITY);
//				int fetchSize = getPropertiesUtil().getIntegerProperty(dataset,
//						JRJdbcQueryExecuterFactory.PROPERTY_JDBC_FETCH_SIZE,
//						0);
//				int maxFieldSize = getPropertiesUtil().getIntegerProperty(dataset,
//						JRJdbcQueryExecuterFactory.PROPERTY_JDBC_MAX_FIELD_SIZE,
//						0);//FIXMENOW check the default of all zero default properties
//				Integer reportMaxCount = (Integer) getParameterValue(JRParameter.REPORT_MAX_COUNT);
				
				statement = connection.prepareStatement(queryString);
				

				visitQueryParameters(new QueryParameterVisitor()
				{
					int paramIdx = 1;
					
					@Override
					public void visit(QueryParameter queryParameter)
					{
						try
						{
							if (queryParameter.isMulti())
							{
								paramIdx += setStatementMultiParameters(paramIdx, queryParameter.getName(), queryParameter.isIgnoreNulls());
							}
							else
							{
								setStatementParameter(paramIdx, queryParameter.getName());
								++paramIdx;
							}
						}
						catch (SQLException e)
						{
							throw new VisitExceptionWrapper(e);
						}
					}
					
					@Override
					public void visit(ValuedQueryParameter valuedQueryParameter)
					{
						// assuming a single value for now
						Class<?> type = valuedQueryParameter.getType();
						Object value = valuedQueryParameter.getValue();
						if (type == null)
						{
							type = value == null ? Object.class : value.getClass();
						}
						
						if (logger.isLoggable(Level.FINE))
						{
							logger.fine("Parameter #" + paramIdx + " (of type " + type.getName() + "): " + value);
						}
						
						try
						{
							setStatementParameter(paramIdx, type, value);// using only dataset properties for now
							++paramIdx;
						}
						catch (SQLException e)
						{
							throw new VisitExceptionWrapper(e);
						}
					}
				});
			}
			catch (VisitExceptionWrapper e)
			{
				throw new JRException("Error preparing statement for executing the report query : " + "\n\n" + queryString + "\n\n", 
						e.getCause());
			}
			catch (SQLException e)
			{
				throw new JRException("Error preparing statement for executing the report query : " + "\n\n" + queryString + "\n\n", e);
			}
		}
	}


	public IResultSet getResultSet() {
		return resultSet;
	}


	protected void setStatementParameter(int parameterIndex, String parameterName) throws SQLException
	{
		JRValueParameter parameter = getValueParameter(parameterName);
		Class<?> clazz = parameter.getValueClass();
		Object parameterValue = parameter.getValue();
		
		if (logger.isLoggable(Level.FINE))
		{
			logger.fine("Parameter #" + parameterIndex + " (" + parameterName + " of type " + clazz.getName() + "): " + parameterValue);
		}

		setStatementParameter(parameterIndex, clazz, parameterValue);
	}


	protected int setStatementMultiParameters(int parameterIndex, String parameterName, boolean ignoreNulls) throws SQLException
	{
		JRValueParameter parameter = getValueParameter(parameterName);
		Object paramValue = parameter.getValue();
		int count;
		int index = 0;
		if (paramValue.getClass().isArray())
		{
			int arrayCount = Array.getLength(paramValue);
			for (count = 0; count < arrayCount; ++count)
			{
				Object value = Array.get(paramValue, count);
				if(!ignoreNulls || value != null)
				{
					setStatementMultiParameter(parameterIndex + index, parameterName, count, value, parameter);
					++index;
				}
			}
		}
		else if (paramValue instanceof Collection<?>)
		{
			Collection<?> values = (Collection<?>) paramValue;
			count = 0;
			for (Iterator<?> it = values.iterator(); it.hasNext(); ++count)
			{
				Object value = it.next();
				
				if(!ignoreNulls || value != null)
				{
					setStatementMultiParameter(parameterIndex + index, parameterName, count, value, parameter);
					++index;
				}
			}
		}
		else
		{
			throw new JRRuntimeException("Multi parameter value is not array nor collection.");
		}
		return index;
	}

	
	protected void setStatementMultiParameter(int parameterIndex, String parameterName, int valueIndex, Object value,
			JRPropertiesHolder properties) throws SQLException
	{
		if (value == null)
		{
			throw new JRRuntimeException("Multi parameters cannot contain null values.");
		}
		
		Class<?> type = value.getClass();
		
		if (logger.isLoggable(Level.FINE))
		{
			logger.fine("Parameter #" + parameterIndex + 
					" (" + parameterName + "[" + valueIndex + "] of type " + type.getName() + "): " + value);
		}
		
		setStatementParameter(parameterIndex, type, value);
	}

	
	protected void setStatementParameter(int parameterIndex, Class<?> parameterType, Object parameterValue) throws SQLException
	{
		// NOTICE (20.06.2013, Donat, Digireport): Boolean should translate to 1 char
		if (java.lang.Boolean.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, SqlType.CHAR);
			}
			else
			{
				statement.setBoolean(parameterIndex, ((Boolean)parameterValue).booleanValue());
			}
		}
		else if (java.lang.Byte.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, SqlType.TINYINT);
			}
			else
			{
				statement.setByte(parameterIndex, ((Byte)parameterValue).byteValue());
			}
		}
		else if (java.lang.Double.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, SqlType.DOUBLE);
			}
			else
			{
				statement.setDouble(parameterIndex, ((Double)parameterValue).doubleValue());
			}
		}
		else if (java.lang.Float.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, SqlType.FLOAT);
			}
			else
			{
				statement.setFloat(parameterIndex, ((Float)parameterValue).floatValue());
			}
		}
		else if (java.lang.Integer.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, SqlType.INTEGER);
			}
			else
			{
				statement.setInt(parameterIndex, ((Integer)parameterValue).intValue());
			}
		}
		else if (java.lang.Long.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, SqlType.BIGINT);
			}
			else
			{
				statement.setLong(parameterIndex, ((Long)parameterValue).longValue());
			}
		}
		else if (java.lang.Short.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, SqlType.SMALLINT);
			}
			else
			{
				statement.setShort(parameterIndex, ((Short)parameterValue).shortValue());
			}
		}
		else if (java.math.BigDecimal.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, SqlType.DECIMAL);
			}
			else
			{
				statement.setDecimal(parameterIndex, (BigDecimal)parameterValue);
			}
		}
		else if (java.lang.String.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, SqlType.VARCHAR);
			}
			else
			{
				statement.setString(parameterIndex, parameterValue.toString());
			}
		}
		else if (ITimestamp.class.isAssignableFrom(parameterType))
		{
			statement.setNull(parameterIndex, SqlType.TIMESTAMP);
		}
		else if (ITime.class.isAssignableFrom(parameterType))
		{
			statement.setNull(parameterIndex, SqlType.TIME);
		}
		else if (IDate.class.isAssignableFrom(parameterType))
		{
			statement.setNull(parameterIndex, SqlType.DATE);
		}
		// NOTICE (20.06.2013, Donat, Digireport): No custom types support
	}



	protected Calendar getParameterCalendar(JRPropertiesHolder properties)
	{
		TimeZone tz;
		if (timeZoneOverride)
		{
			// if we have a parameter, use it
			tz = timeZone;
		}
		else
		{
			if (properties.hasProperties() && properties.getPropertiesMap().containsProperty(
					JRJdbcQueryExecuterFactory.PROPERTY_TIME_ZONE))
			{
				// read the parameter level property
				String timezoneId = getPropertiesUtil().getProperty(properties, 
						JRJdbcQueryExecuterFactory.PROPERTY_TIME_ZONE);
				tz = (timezoneId == null || timezoneId.length() == 0) ? null 
						: TimeZone.getTimeZone(timezoneId);
			}
			else
			{
				// dataset/default property
				tz = timeZone;
			}
		}

		// using default JVM locale for the calendar
		Calendar cal = tz == null ? null : Calendar.getInstance(tz);
		return cal;
	}
	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.util.JRQueryExecuter#close()
	 */
	public synchronized void close()
	{
		if (resultSet != null)
		{
			try
			{
				resultSet.close();
			}
			catch (IOException e)
			{
				logger.log(Level.SEVERE,"Error while closing result set.", e);
			}
			finally
			{
				resultSet = null;
			}
		}
		
		if (statement != null)
		{
			try
			{
				statement.close();
			}
			catch (IOException e)
			{
				logger.log(Level.SEVERE,"Error while closing statement.", e);
			}
			finally
			{
				statement = null;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.util.JRQueryExecuter#cancelQuery()
	 */
	public synchronized boolean cancelQuery() throws JRException
	{
		if (statement != null)
		{
			try
			{
				statement.cancel();
				return true;
			}
			catch (Exception e)
			{
				throw new JRException("Error cancelling SQL statement", e);
			}
		}
		
		return false;
	}
		
}
