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
package org.oss.pdfreporter.crosstabs.xml;

import org.oss.pdfreporter.crosstabs.design.JRDesignCrosstabColumnGroup;
import org.oss.pdfreporter.crosstabs.type.CrosstabColumnPositionEnum;
import org.oss.pdfreporter.xml.parsers.IAttributes;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRCrosstabColumnGroupFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRCrosstabColumnGroupFactory extends JRCrosstabGroupFactory
{
	public static final String ATTRIBUTE_height = "height";
	public static final String ATTRIBUTE_headerPosition = "headerPosition";

	public Object createObject(IAttributes attributes)
	{
		JRDesignCrosstabColumnGroup group = new JRDesignCrosstabColumnGroup();
		
		setGroupAtts(attributes, group);
		
		String heightAttr = attributes.getValue(ATTRIBUTE_height);
		if (heightAttr != null)
		{
			group.setHeight(Integer.parseInt(heightAttr));
		}
		
		CrosstabColumnPositionEnum position = CrosstabColumnPositionEnum.getByName(attributes.getValue(ATTRIBUTE_headerPosition));
		if (position != null)
		{
			group.setPosition(position);
		}
		
		return group;
	}

}
