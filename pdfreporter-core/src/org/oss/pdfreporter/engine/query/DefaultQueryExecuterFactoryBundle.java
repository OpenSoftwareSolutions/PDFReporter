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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRPropertiesUtil;
import org.oss.pdfreporter.engine.JRPropertiesUtil.PropertySuffix;
import org.oss.pdfreporter.engine.util.JRQueryExecuterUtils;
import org.oss.pdfreporter.engine.util.JRSingletonCache;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: DefaultQueryExecuterFactoryBundle.java 5050 2012-03-12 10:11:26Z teodord $
 */
public final class DefaultQueryExecuterFactoryBundle implements JRQueryExecuterFactoryBundle
{
	@SuppressWarnings("deprecation")
	private static final JRSingletonCache<JRQueryExecuterFactory> cache = 
			new JRSingletonCache<JRQueryExecuterFactory>(JRQueryExecuterFactory.class);
	
	private static final DefaultQueryExecuterFactoryBundle INSTANCE = new DefaultQueryExecuterFactoryBundle();
	
	private DefaultQueryExecuterFactoryBundle()
	{
	}
	
	/**
	 * 
	 */
	public static DefaultQueryExecuterFactoryBundle getInstance()
	{
		return INSTANCE;
	}

	/**
	 * 
	 */
	public String[] getLanguages()
	{
		List<String> languages = new ArrayList<String>();
		List<PropertySuffix> properties = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).getProperties(QueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX);
		for (Iterator<PropertySuffix> it = properties.iterator(); it.hasNext();)
		{
			PropertySuffix property = it.next();
			languages.add(property.getSuffix());
		}
		return languages.toArray(new String[languages.size()]);
	}

	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	public QueryExecuterFactory getQueryExecuterFactory(String language) throws JRException
	{
		String factoryClassName = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).getProperty(QueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX + language);
		if (factoryClassName == null)
		{
			return null;
		}
		
		JRQueryExecuterFactory factory = cache.getCachedInstance(factoryClassName);
		if (factory instanceof QueryExecuterFactory)
		{
			return (QueryExecuterFactory)factory;
		}
		
		return new JRQueryExecuterUtils.WrappingQueryExecuterFactory(factory);
	}

}
