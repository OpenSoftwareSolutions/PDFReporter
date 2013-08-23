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

/*
 * Contributors:
 * S. Brett Sutton - bsutton@idatam.com.au
 */
package org.oss.pdfreporter.engine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.query.JRJdbcQueryExecuterFactory;
import org.oss.pdfreporter.image.IImage;
import org.oss.pdfreporter.sql.IBlob;
import org.oss.pdfreporter.sql.IDate;
import org.oss.pdfreporter.sql.IResultMetaData;
import org.oss.pdfreporter.sql.IResultSet;
import org.oss.pdfreporter.sql.ITime;
import org.oss.pdfreporter.sql.ITimestamp;
import org.oss.pdfreporter.sql.SQLException;
import org.oss.pdfreporter.sql.SqlType;



/**
 * An implementation of a data source that uses a supplied <tt>IResultSet</tt>.
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRResultSetDataSource.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRResultSetDataSource implements JRDataSource
{
	private final static Logger logger = Logger.getLogger(JRResultSetDataSource.class.getName());


	private static final String INDEXED_COLUMN_PREFIX = "COLUMN_";
	private static final int INDEXED_COLUMN_PREFIX_LENGTH = INDEXED_COLUMN_PREFIX.length();
	
	/**
	 *
	 */
	private JasperReportsContext jasperReportsContext;
	private IResultSet resultSet;
	private Map<String,Integer> columnIndexMap = new HashMap<String,Integer>();

	// TODO (20.06.2013, Donat, Digireport): No time zone support for now
	private TimeZone timeZone;
	private boolean timeZoneOverride;
	private Map<JRField, Calendar> fieldCalendars = new HashMap<JRField, Calendar>();


	/**
	 *
	 */
	public JRResultSetDataSource(JasperReportsContext jasperReportsContext, IResultSet resultSet)
	{
		this.jasperReportsContext = jasperReportsContext;
		this.resultSet = resultSet;
	}


	/**
	 * @see #JRResultSetDataSource(JasperReportsContext, IResultSet)
	 */
	public JRResultSetDataSource(IResultSet resultSet)
	{
		this(DefaultJasperReportsContext.getInstance(), resultSet);
	}


	/**
	 *
	 */
	public boolean next() throws JRException
	{
		boolean hasNext = false;
		
		if (resultSet != null)
		{
			try
			{
				hasNext = resultSet.next();
			}
			catch (SQLException e)
			{
				throw new JRException("Unable to get next record.", e);
			}
		}
		
		return hasNext;
	}


	/**
	 *
	 */
	// TODO (19.07.2013, Donat, Digireport): Added support for sql datatypes better to convert datatypes while loading jrxml
	public Object getFieldValue(JRField field) throws JRException
	{
		Object objValue = null;

		if (field != null && resultSet != null)
		{
			Integer columnIndex = getColumnIndex(field.getName());
			Class<?> clazz = field.getValueClass();

			try
			{
				if (clazz.equals(java.lang.Boolean.class))
				{
					objValue = resultSet.getBoolean(columnIndex.intValue()) ? Boolean.TRUE : Boolean.FALSE;
				}
				else if (clazz.equals(java.lang.Byte.class))
				{
					objValue = new Byte(resultSet.getByte(columnIndex.intValue()));
					if(resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (
					clazz.equals(java.util.Date.class)
					|| clazz.equals(org.oss.pdfreporter.sql.IDate.class)
					|| clazz.equals(java.sql.Date.class)
					)
				{
					objValue = readDate(columnIndex, field);
				}
				else if (clazz.equals(org.oss.pdfreporter.sql.ITimestamp.class) || clazz.equals(java.sql.Timestamp.class))
				{
					objValue = readTimestamp(columnIndex, field);
				}
				else if (clazz.equals(org.oss.pdfreporter.sql.ITime.class) || clazz.equals(java.sql.Time.class))
				{
					objValue = readTime(columnIndex, field);
				}
				else if (clazz.equals(java.lang.Double.class))
				{
					objValue = new Double(resultSet.getDouble(columnIndex.intValue()));
					if(resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.Float.class))
				{
					objValue = new Float(resultSet.getFloat(columnIndex.intValue()));
					if(resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.Integer.class))
				{
					objValue = Integer.valueOf(resultSet.getInt(columnIndex.intValue()));
					if(resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.io.InputStream.class))
				{
					byte[] bytes = readBytes(columnIndex);
					
					if(bytes == null)
					{
						objValue = null;
					}
					else
					{
						objValue = new ByteArrayInputStream(bytes);
					}					
				}
				else if (clazz.equals(java.lang.Long.class))
				{
					objValue = new Long(resultSet.getLong(columnIndex.intValue()));
					if(resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.Short.class))
				{
					objValue = new Short(resultSet.getShort(columnIndex.intValue()));
					if(resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.math.BigDecimal.class))
				{
					objValue = resultSet.getDecimal(columnIndex.intValue());
					if(resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.String.class))
				{
					SqlType columnType = resultSet.getMetaData().getColumnType(columnIndex.intValue());
					switch (columnType)
					{
						// no clob support
						default:
							objValue = resultSet.getString(columnIndex.intValue());
							if(resultSet.wasNull())
							{
								objValue = null;
							}
							break;
					}
				}
				else if (clazz.equals(IBlob.class))
				{
					objValue = resultSet.getBlob(columnIndex.intValue());
					if(resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(IImage.class))
				{
					byte[] bytes = readBytes(columnIndex);
					
					if(bytes == null)
					{
						objValue = null;
					}
					else
					{
						// TODO (29.04.2013, Donat, Digireport): Add support for memory images JRImageLoader.getInstance(jasperReportsContext).loadAwtImageFromBytes(bytes);
						logger.warning("Images from Database are not supported.");
						objValue = null;
					}					
				}
				else
				{
					objValue = resultSet.getObject(columnIndex.intValue());
				}
			}
			catch (Exception e)
			{
				throw new JRException("Unable to get value for field '" + field.getName() + "' of class '" + clazz.getName() + "'", e);
			}
		}
		
		return objValue;
	}


	protected Object readDate(Integer columnIndex, JRField field) throws SQLException
	{
		// NOTICE (20.06.2013, Donat, Digireport): No time zone support for now Calendar calendar = getFieldCalendar(field);		
		Object objValue = null;
		IDate value =  resultSet.getDate(columnIndex.intValue());
		if(!resultSet.wasNull())
		{
			// TODO (20.06.2013, Donat, Digireport): Add support to IDate or IDateTime instead of java.util.date	
			objValue = value.getDate();
		} 
		return objValue;
	}


	protected Object readTimestamp(Integer columnIndex, JRField field) throws SQLException
	{
		// NOTICE (20.06.2013, Donat, Digireport): No time zone support for now Calendar calendar = getFieldCalendar(field);		
		Object objValue = null;
		ITimestamp value = resultSet.getTimestamp(columnIndex.intValue());
		if(!resultSet.wasNull())
		{
			// TODO (20.06.2013, Donat, Digireport): Add support to ITimestamp instead of java.util.date	
			objValue = new Date(value.getMilliseconds());
		} 
		return objValue;
	}


	protected Object readTime(Integer columnIndex, JRField field) throws SQLException
	{
		// NOTICE (20.06.2013, Donat, Digireport): No time zone support for now Calendar calendar = getFieldCalendar(field);		
		Object objValue = null;
		ITime value = resultSet.getTime(columnIndex.intValue());
		if(!resultSet.wasNull())
		{
			// TODO (20.06.2013, Donat, Digireport): Add support to use ITime instead of java.util.date	
			objValue = value.getDate();
		} 
		return objValue;
	}





	/**
	 *
	 */
	private Integer getColumnIndex(String fieldName) throws JRException
	{
		Integer columnIndex = columnIndexMap.get(fieldName);
		if (columnIndex == null)
		{
			try
			{
				columnIndex = searchColumnByName(fieldName);
				
				if (columnIndex == null)
				{
					columnIndex = searchColumnByLabel(fieldName);
				}
				
				if (columnIndex == null && fieldName.startsWith(INDEXED_COLUMN_PREFIX))
				{
					columnIndex = Integer.valueOf(fieldName.substring(INDEXED_COLUMN_PREFIX_LENGTH));
					if (
						columnIndex.intValue() <= 0
						|| columnIndex.intValue() > resultSet.getMetaData().getColumnCount()
						)
					{
						throw new JRException("Column index out of range : " + columnIndex);
					}
				}
				
				if (columnIndex == null)
				{
					throw new JRException("Unknown column name : " + fieldName);
				}
			}
			catch (SQLException e)
			{
				throw new JRException("Unable to retrieve result set metadata.", e);
			}

			columnIndexMap.put(fieldName, columnIndex);
		}
		
		return columnIndex;
	}


	protected Integer searchColumnByName(String fieldName) throws SQLException
	{
		Integer columnIndex = null;
		IResultMetaData metadata = resultSet.getMetaData();
		for(int i = 1; i <= metadata.getColumnCount(); i++)
		{
			String columnName = metadata.getColumnName(i);
			if (fieldName.equalsIgnoreCase(columnName))
			{
				columnIndex = Integer.valueOf(i);
				break;
			}
		}
		return columnIndex;
	}


	protected Integer searchColumnByLabel(String fieldName) throws SQLException
	{
		Integer columnIndex = null;
		IResultMetaData metadata = resultSet.getMetaData();
		for(int i = 1; i <= metadata.getColumnCount(); i++)
		{
			String columnLabel = metadata.getColumnLabel(i);
			if (columnLabel != null && fieldName.equalsIgnoreCase(columnLabel))
			{
				columnIndex = Integer.valueOf(i);
				break;
			}
		}
		return columnIndex;
	}




	protected byte[] readBytes(Integer columnIndex) throws SQLException, IOException
	{
		byte[] bytes = null;
		
		SqlType columnType = resultSet.getMetaData().getColumnType(columnIndex.intValue());
		switch (columnType)
		{
			case BLOB:
			{
				IBlob blob = resultSet.getBlob(columnIndex.intValue());
				if (!resultSet.wasNull())
				{
					bytes = blob.getBytes();
				}
				break;
			}	
			default:
			{
				throw new SQLException("Binary data requires BLOB column type to access.");
			}
		}
				
		return bytes;
	}


	/**
	 * Sets the default time zone to be used for retrieving date/time values from the 
	 * result set.
	 * 
	 * In most cases no explicit time zone conversion would be required for retrieving 
	 * date/time values from the DB, and this parameter should be null.  
	 * 
	 * @param timeZone the default time zone
	 * @param override whether the default time zone overrides time zones specified
	 * as field-level properties
	 * @see JRJdbcQueryExecuterFactory#PROPERTY_TIME_ZONE
	 */
	public void setTimeZone(TimeZone timeZone, boolean override)
	{
		this.timeZone = timeZone;
		this.timeZoneOverride = override;
	}
	
	protected Calendar getFieldCalendar(JRField field)
	{
		if (fieldCalendars.containsKey(field))
		{
			return fieldCalendars.get(field);
		}
		
		Calendar calendar = createFieldCalendar(field);
		fieldCalendars.put(field, calendar);
		return calendar;
	}

	protected Calendar createFieldCalendar(JRField field)
	{
		TimeZone tz;
		if (timeZoneOverride)
		{
			// if we have a parameter, use it
			tz = timeZone;
		}
		else
		{
			if (field.hasProperties() && field.getPropertiesMap().containsProperty(
					JRJdbcQueryExecuterFactory.PROPERTY_TIME_ZONE))
			{
				// read the field level property
				String timezoneId = JRPropertiesUtil.getInstance(jasperReportsContext).getProperty(field, 
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
}
