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

import java.io.File;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JRDataset;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRValueParameter;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.data.JRXmlDataSource;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;



/**
 * XPath query executer implementation.
 * <p/>
 * The XPath query of the report is executed against the document specified by the
 * {@link org.oss.pdfreporter.engine.query.JRXPathQueryExecuterFactory#PARAMETER_XML_DATA_DOCUMENT PARAMETER_XML_DATA_DOCUMENT}
 * parameter.
 * <p/>
 * All the parameters in the XPath query are replaced by calling <code>String.valueOf(Object)</code>
 * on the parameter value.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRXPathQueryExecuter.java 5576 2012-08-16 09:04:00Z lucianc $
 */
public class JRXPathQueryExecuter extends JRAbstractQueryExecuter
{
	protected static final String CANONICAL_LANGUAGE = "XPath";
	
	private JRXmlDataSource datasource;

	/**
	 * 
	 */
	public JRXPathQueryExecuter(
		JasperReportsContext jasperReportsContext,
		JRDataset dataset, 
		Map<String,? extends JRValueParameter> parametersMap
		)
	{
		super(jasperReportsContext, dataset, parametersMap);
				
		parseQuery();
	}

	/**
	 * @deprecated Replaced by {@link #JRXPathQueryExecuter(JasperReportsContext, JRDataset, Map)}.
	 */
	public JRXPathQueryExecuter(JRDataset dataset, Map<String,? extends JRValueParameter> parametersMap)
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
		JRXmlDataSource datasource = null;
		
		String xPath = getQueryString();
		
		if (xPath != null)//FIXME maybe we should create data source with no select expression too
		{
			Document document = (Document) getParameterValue(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT);
			if (document != null) {
				datasource = new JRXmlDataSource(getJasperReportsContext(), document, xPath);
			} else {
				InputStream xmlInputStream = (InputStream) getParameterValue(JRXPathQueryExecuterFactory.XML_INPUT_STREAM);
				if (xmlInputStream != null) {
					datasource = new JRXmlDataSource(getJasperReportsContext(), xmlInputStream, xPath);
				} else {
					File xmlFile = (File) getParameterValue(JRXPathQueryExecuterFactory.XML_FILE);
					if (xmlFile != null) {
						datasource = new JRXmlDataSource(getJasperReportsContext(), xmlFile, xPath);
					} else {
						String xmlSource = getStringParameterOrProperty(JRXPathQueryExecuterFactory.XML_SOURCE);
						if (xmlSource != null) {
							datasource = new JRXmlDataSource(getJasperReportsContext(), xmlSource, xPath);
						} else {
						}
					}
				}
			}

			if (datasource != null)
			{
				datasource.setLocale((Locale)getParameterValue(JRXPathQueryExecuterFactory.XML_LOCALE, true));
				datasource.setDatePattern(getStringParameter(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, JRXPathQueryExecuterFactory.PROPERTY_XML_DATE_PATTERN));
				datasource.setNumberPattern(getStringParameter(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, JRXPathQueryExecuterFactory.PROPERTY_XML_NUMBER_PATTERN));
				datasource.setTimeZone((TimeZone)getParameterValue(JRXPathQueryExecuterFactory.XML_TIME_ZONE, true));
			}
		}
		
		return datasource;
	}

	public void close()
	{
		if(datasource != null){
			datasource.close();
		}
	}

	public boolean cancelQuery() throws JRException
	{
		//nothing to cancel
		return false;
	}
	
}
