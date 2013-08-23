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
package org.oss.pdfreporter.engine.xml;

import java.util.List;


import org.oss.pdfreporter.extensions.ExtensionsEnvironment;
import org.oss.pdfreporter.extensions.ExtensionsRegistry;
import org.oss.pdfreporter.uses.org.apache.commons.collections.ReferenceMap;

/**
 * Class the provides access to {@link XmlValueHandler XML value handlers}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: XmlValueHandlerUtils.java 4808 2011-11-21 13:44:22Z lucianc $
 */
public class XmlValueHandlerUtils
{

	private static final XmlValueHandlerUtils INSTANCE = new XmlValueHandlerUtils();
	
	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static XmlValueHandlerUtils instance()
	{
		return INSTANCE;
	}
	
	private final ReferenceMap cache;
	
	private XmlValueHandlerUtils()
	{
		cache = new ReferenceMap(ReferenceMap.WEAK, ReferenceMap.HARD);
	}
	
	/**
	 * Returns the list of XML value handlers.
	 * 
	 * @return the list of XML value handlers
	 */
	public List<XmlValueHandler> getHandlers()
	{
		Object cacheKey = ExtensionsEnvironment.getExtensionsCacheKey();
		synchronized (cache)
		{
			@SuppressWarnings("unchecked")
			List<XmlValueHandler> handlers = (List<XmlValueHandler>) cache.get(cacheKey);
			if (handlers == null)
			{
				ExtensionsRegistry extensionsRegistry = ExtensionsEnvironment.getExtensionsRegistry();
				handlers = extensionsRegistry.getExtensions(XmlValueHandler.class);
				cache.put(cacheKey, handlers);
			}
			return handlers;
		}
	}

	
}
