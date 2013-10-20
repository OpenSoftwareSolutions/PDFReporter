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

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRDataset;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRPropertiesUtil;
import org.oss.pdfreporter.engine.JRResultSetDataSource;
import org.oss.pdfreporter.engine.JRValueParameter;
import org.oss.pdfreporter.engine.JasperReportsContext;


/**
 * Query executer factory for SQL queries.
 * <p/>
 * This factory creates JDBC query executers for SQL queries.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRJdbcQueryExecuterFactory.java 5305 2012-04-26 15:17:33Z teodord $
 * @see org.oss.pdfreporter.engine.query.JRJdbcQueryExecuter
 */
public class JRJdbcQueryExecuterFactory extends AbstractQueryExecuterFactory
{	
	private final static Logger logger = Logger.getLogger(JRJdbcQueryExecuterFactory.class.getName());
	/**
	 * Property specifying the ResultSet fetch size.
	 */
	public static final String PROPERTY_JDBC_FETCH_SIZE = JRPropertiesUtil.PROPERTY_PREFIX + "jdbc.fetch.size";

	/**
	 * Property specifying the ResultSet type.
	 */
	public static final String PROPERTY_JDBC_RESULT_SET_TYPE = JRPropertiesUtil.PROPERTY_PREFIX + "jdbc.result.set.type";

	/**
	 * Property specifying the ResultSet concurrency.
	 */
	public static final String PROPERTY_JDBC_CONCURRENCY = JRPropertiesUtil.PROPERTY_PREFIX + "jdbc.concurrency";

	/**
	 * Property specifying the ResultSet holdability.
	 */
	public static final String PROPERTY_JDBC_HOLDABILITY = JRPropertiesUtil.PROPERTY_PREFIX + "jdbc.holdability";

	/**
	 * Property specifying the statement max field size.
	 */
	public static final String PROPERTY_JDBC_MAX_FIELD_SIZE = JRPropertiesUtil.PROPERTY_PREFIX + "jdbc.max.field.size";

	/**
	 * Flag property specifying if data will be stored in a cached rowset.
	 */
	public static final String PROPERTY_CACHED_ROWSET = JRPropertiesUtil.PROPERTY_PREFIX + "jdbc.cached.rowset";

	/**
	 * Property specifying the default time zone to be used for sending and retrieving 
	 * date/time values to and from the database.
	 * 
	 * In most cases no explicit time zone conversion would be required, and this property 
	 * should not be set.
	 * 
	 * <p>
	 * The property can be set globally, at dataset level, at parameter and field levels,
	 * and as a report/dataset parameter.  Note that sending a value as parameter will 
	 * override all properties, and the time zone will be used for all date/time parameters
	 * and fields in the report. 
	 * </p>
	 * 
	 * @see JRResultSetDataSource#setTimeZone(java.util.TimeZone, boolean)
	 */
	public static final String PROPERTY_TIME_ZONE = JRPropertiesUtil.PROPERTY_PREFIX + "jdbc.time.zone";

	/**
	 * SQL query language.
	 */
	public static final String QUERY_LANGUAGE_SQL = "sql";
	
	
	private static final String[] queryParameterClassNames;
	
	static
	{
		queryParameterClassNames = new String[] {
				"java.lang.Object", 
				"java.lang.Boolean", 
				"java.lang.Byte", 
				"java.lang.Double",
				"java.lang.Float", 
				"java.lang.Integer", 
				"java.lang.Long", 
				"java.lang.Short", 
				"java.math.BigDecimal",
				"java.lang.String", 
				"java.util.Date", 
				"org.oss.pdfreporter.sql.IDateTime", 
				"org.oss.pdfreporter.sql.IDate", 
				"org.oss.pdfreporter.sql.ITimestamp", 
				"org.oss.pdfreporter.sql.ITime" };

		Arrays.sort(queryParameterClassNames);
	}
	
	public JRQueryExecuter createQueryExecuter(
		JasperReportsContext jasperReportsContext,
		JRDataset dataset, 
		Map<String,? extends JRValueParameter> parameters
		) throws JRException
	{
		return new JRJdbcQueryExecuter(jasperReportsContext, dataset, parameters);
	}

	public Object[] getBuiltinParameters()
	{
		return null;
	}

	public boolean supportsQueryParameterType(String className)
	{
		boolean supported =  Arrays.binarySearch(queryParameterClassNames, className) >= 0;
		if (!supported) {
			logger.warning("Parameter class '" + className + "' not found in supported types: " +Arrays.asList(queryParameterClassNames));
		}
		return supported;
	}
}
