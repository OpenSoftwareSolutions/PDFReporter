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

import org.oss.pdfreporter.engine.type.EnumUtil;
import org.oss.pdfreporter.engine.type.JREnum;
import org.oss.pdfreporter.engine.type.NamedEnum;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: XmlConstantPropertyRule.java 5499 2012-07-20 11:34:40Z lucianc $
 */
public class XmlConstantPropertyRule extends TransformedPropertyRule
{

	private final NamedEnum[] values;

	public XmlConstantPropertyRule(String attributeName, JREnum[] values)
	{
		this(attributeName, (NamedEnum[]) values);
	}

	public XmlConstantPropertyRule(String attributeName, String propertyName, 
			JREnum[] values)
	{
		this(attributeName, propertyName, (NamedEnum[]) values);
	}

	public XmlConstantPropertyRule(String attributeName, NamedEnum[] values)
	{
		super(attributeName);
		this.values = values;
	}

	public XmlConstantPropertyRule(String attributeName, String propertyName, 
			NamedEnum[] values)
	{
		super(attributeName, propertyName);
		this.values = values;
	}

	protected Object toPropertyValue(String attributeValue)
	{
		Object value = EnumUtil.getEnumByName(values, attributeValue);
		return value;
	}
	
}
