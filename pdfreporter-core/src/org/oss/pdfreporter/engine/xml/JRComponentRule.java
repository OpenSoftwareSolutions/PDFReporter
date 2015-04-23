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

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.component.Component;
import org.oss.pdfreporter.engine.component.ComponentKey;
import org.oss.pdfreporter.engine.design.JRDesignComponentElement;
import org.oss.pdfreporter.uses.org.apache.digester.AbstractRule;


/**
 * A digester rule that links a {@link Component} object with its parent
 * {@link JRDesignComponentElement}.
 *
 * <p>
 * This rules also sets the {@link ComponentKey component type key} on the
 * component element via
 * {@link JRDesignComponentElement#setComponentKey(ComponentKey)}.
 * The component type key is created based on information from the XML
 * component node; the node namespace is used as component type namespace
 * and the node name is used as component name.
 *
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRComponentRule.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class JRComponentRule extends AbstractRule
{

	public static JRComponentRule newInstance()
	{
		return new JRComponentRule();
	}

	public void end(String namespace, String name) throws JRException
	{
		Object top = getDigester().peek();
		if (!(top instanceof Component))
		{
			throw new JRException("Object of type " + top.getClass().getName() + " is not a "
					+ Component.class.getName() + " instance");
		}

		Component component = (Component) top;
		JRDesignComponentElement componentElement = (JRDesignComponentElement) getDigester().peek(1);
		String namespacePrefix = ((JRXmlDigester) getDigester().getDelegator()).getLastNamespacePrefix();
		ComponentKey componentKey = new ComponentKey(namespace, namespacePrefix, name);
		componentElement.setComponentKey(componentKey);
		componentElement.setComponent(component);
	}

}
