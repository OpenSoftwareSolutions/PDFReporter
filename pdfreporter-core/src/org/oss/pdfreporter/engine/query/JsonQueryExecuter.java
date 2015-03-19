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
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JRDataset;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRValueParameter;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.json.IJsonDataSource;
import org.oss.pdfreporter.json.factory.IJsonDataSourceFactory;
import org.oss.pdfreporter.registry.ApiRegistry;

/**
 * JSON query executer implementation.
 *
 * @author Narcis Marcu (narcism@users.sourceforge.net)
 * @version $Id: JsonQueryExecuter.java 5576 2012-08-16 09:04:00Z lucianc $
 */
public class JsonQueryExecuter extends JRAbstractQueryExecuter
{
	private static final Logger log = Logger.getLogger(JsonQueryExecuter.class.getName());

	protected static final String CANONICAL_LANGUAGE = "JSON";

	private IJsonDataSource datasource;

	/**
	 *
	 */
	public JsonQueryExecuter(
		JasperReportsContext jasperReportsContext,
		JRDataset dataset,
		Map<String, ? extends JRValueParameter> parametersMap
		)
	{
		super(jasperReportsContext, dataset, parametersMap);
		parseQuery();
	}

	/**
	 * @deprecated Replaced by {@link #JsonQueryExecuter(JasperReportsContext, JRDataset, Map)}.
	 */
	public JsonQueryExecuter(JRDataset dataset, Map<String, ? extends JRValueParameter> parametersMap)
	{
		this(DefaultJasperReportsContext.getInstance(), dataset, parametersMap);
	}

	@Override
	protected String getCanonicalQueryLanguage()
	{
		return CANONICAL_LANGUAGE;
	}

	protected String getParameterReplacement(String parameterName)
	{
		return String.valueOf(getParameterValue(parameterName));
	}

	public JRDataSource createDatasource() throws JRException
	{
		IJsonDataSourceFactory jsonDataSourceFactory = ApiRegistry.getJsonDataSourceFactory();
		InputStream jsonInputStream = (InputStream) getParameterValue(JsonQueryExecuterFactory.JSON_INPUT_STREAM);
		if (jsonInputStream != null) {
			datasource = jsonDataSourceFactory.newJsonDataSource(jsonInputStream, getQueryString());
		} else {
			String jsonSource = getStringParameterOrProperty(JsonQueryExecuterFactory.JSON_SOURCE);
			if (jsonSource != null) {
				datasource = jsonDataSourceFactory.newJsonDataSource(getJasperReportsContext(), jsonSource, getQueryString());
			} else {
				if (log.isLoggable(Level.WARNING)) {
					log.warning("No JSON source was provided.");
				}
			}
		}

		if (datasource != null) {
			String dateFormatPattern = getStringParameterOrProperty(JsonQueryExecuterFactory.JSON_DATE_PATTERN);
			if(dateFormatPattern != null){
				datasource.setDatePattern(dateFormatPattern);
			}

			String numberFormatPattern = getStringParameterOrProperty(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN);
			if(numberFormatPattern != null){
				datasource.setNumberPattern(numberFormatPattern);
			}

			Locale jsonLocale = (Locale) getParameterValue(JsonQueryExecuterFactory.JSON_LOCALE, true);
			if (jsonLocale != null) {
				datasource.setLocale(jsonLocale);
			} else {
				String jsonLocaleCode = getStringParameterOrProperty(JsonQueryExecuterFactory.JSON_LOCALE_CODE);
				if (jsonLocaleCode != null) {
					datasource.setLocale(jsonLocaleCode);
				}
			}

			TimeZone jsonTimezone = (TimeZone) getParameterValue(JsonQueryExecuterFactory.JSON_TIME_ZONE, true);
			if (jsonTimezone != null) {
				datasource.setTimeZone(jsonTimezone);
			} else {
				String jsonTimezoneId = getStringParameterOrProperty(JsonQueryExecuterFactory.JSON_TIMEZONE_ID);
				if (jsonTimezoneId != null) {
					datasource.setTimeZone(jsonTimezoneId);
				}
			}
		}

		return datasource;
	}

	public void close()
	{
		if(datasource != null) {
			try {
				datasource.close();
			} catch (IOException e) {
				log.log(Level.SEVERE,"Error while closing datasource.", e);
			}
			finally {
				datasource = null;
			}
		}
	}

	public boolean cancelQuery() throws JRException
	{
		//nothing to cancel
		return false;
	}

}
