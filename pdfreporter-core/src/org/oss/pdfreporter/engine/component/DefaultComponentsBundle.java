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
package org.oss.pdfreporter.engine.component;

import java.util.Map;
import java.util.Set;

import org.oss.pdfreporter.engine.JRRuntimeException;


/**
 * The default {@link ComponentsBundle components bundle} implementation.
 *
 * <p>
 * A components bundle consists of a {@link ComponentsXmlParser XML parser}
 * instance and a map of {@link IComponentManager component managers}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: DefaultComponentsBundle.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class DefaultComponentsBundle implements ComponentsBundle
{

	private ComponentsXmlParser xmlParser;
	private Map<String,IComponentManager> componentManagers;

	public ComponentsXmlParser getXmlParser()
	{
		return xmlParser;
	}

	/**
	 * Sets the components XML parser implementation.
	 * 
	 * @param xmlParser the components XML parser
	 * @see #getXmlParser()
	 */
	public void setXmlParser(ComponentsXmlParser xmlParser)
	{
		this.xmlParser = xmlParser;
	}

	public Set<String> getComponentNames()
	{
		return componentManagers.keySet();
	}
	
	public IComponentManager getComponentManager(String componentName)
	{
		IComponentManager manager = componentManagers.get(componentName);
		if (manager == null)
		{
			throw new JRRuntimeException("No component manager found for name " + componentName 
					+ ", namespace " + xmlParser.getNamespace());
		}
		return manager;
	}
	
	/**
	 * Returns the internal map of component managers, indexed by component name.
	 * 
	 * @return the map of component managers
	 * @see #setComponentManagers(Map)
	 */
	public Map<String,IComponentManager> getComponentManagers()
	{
		return componentManagers;
	}

	/**
	 * Sets the map of component managers.
	 * 
	 * <p>
	 * The map needs to use component names as keys, and {@link IComponentManager}
	 * instances as values.
	 * 
	 * @param componentManagers the map of component managers
	 */
	public void setComponentManagers(Map<String,IComponentManager> componentManagers)
	{
		this.componentManagers = componentManagers;
	}
	
}
