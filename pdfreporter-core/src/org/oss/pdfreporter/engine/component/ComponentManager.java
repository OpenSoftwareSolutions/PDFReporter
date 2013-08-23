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

import org.oss.pdfreporter.engine.JasperReportsContext;

/**
 * A component manager is the entry point through which the handlers for a
 * single component type can be accessed.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ComponentManager.java 5661 2012-09-13 19:00:14Z teodord $
 * @see ComponentsEnvironment#getComponentManager(ComponentKey)
 * @see ComponentsBundle#getComponentManager(String)
 */
public interface ComponentManager extends IComponentManager
{
	
	/**
	 * Returns the factory of fill component instances.
	 * 
	 * @return the factory of fill component instances
	 */
	ComponentFillFactory getComponentFillFactory(JasperReportsContext jasperReportsContext);

	
}
