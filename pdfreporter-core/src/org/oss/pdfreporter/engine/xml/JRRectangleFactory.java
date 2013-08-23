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

import org.oss.pdfreporter.engine.design.JRDesignRectangle;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.xml.parsers.IAttributes;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRRectangleFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRRectangleFactory extends JRBaseFactory
{

	/**
	 *
	 */
	public Object createObject(IAttributes atts)
	{
		JasperDesign jasperDesign = (JasperDesign)digester.peek(digester.getCount() - 2);

		JRDesignRectangle rectangle = new JRDesignRectangle(jasperDesign);
		
		String radius = atts.getValue(JRXmlConstants.ATTRIBUTE_radius);
		if (radius != null && radius.length() > 0)
		{
			rectangle.setRadius(Integer.parseInt(radius));
		}

		return rectangle;
	}


}
