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
 * A default {@link IComponentManager component manager} implementation.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: DefaultComponentManager.java 5781 2012-11-05 13:43:56Z teodord $
 */
public class DefaultComponentManager implements IComponentManager
{

	private ComponentCompiler componentCompiler;

	
	public ComponentCompiler getComponentCompiler(JasperReportsContext jasperReportsContext)
	{
		return componentCompiler;
	}

	/**
	 * Sets the component compiler implementation.
	 * 
	 * @param componentCompiler the component compiler
	 * @see #getComponentCompiler(JasperReportsContext)
	 */
	public void setComponentCompiler(ComponentCompiler componentCompiler)
	{
		this.componentCompiler = componentCompiler;
	}

	
}
