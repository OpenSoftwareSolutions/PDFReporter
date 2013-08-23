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

import java.util.Map;

import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JRStyle;
import org.oss.pdfreporter.engine.JasperPrint;
import org.oss.pdfreporter.engine.base.JRBasePrintElement;
import org.oss.pdfreporter.engine.type.ModeEnum;
import org.oss.pdfreporter.engine.util.JRColorUtil;
import org.oss.pdfreporter.uses.java.util.UUID;
import org.oss.pdfreporter.xml.parsers.IAttributes;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPrintElementFactory.java 5542 2012-08-03 12:39:11Z teodord $
 */
public class JRPrintElementFactory extends JRBaseFactory
{

	/**
	 *
	 */
	public Object createObject(IAttributes atts)
	{
		JRPrintXmlLoader printXmlLoader = (JRPrintXmlLoader)digester.peek(digester.getCount() - 1);
		JasperPrint jasperPrint = (JasperPrint)digester.peek(digester.getCount() - 2);
		JRBasePrintElement element = (JRBasePrintElement)digester.peek();

		String key = atts.getValue(JRXmlConstants.ATTRIBUTE_key);
		if (key != null)
		{
			element.setKey(key);
		}
		
		ModeEnum mode = ModeEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_mode));
		if (mode != null)
		{
			element.setMode(mode);
		}
		
		String uuid = atts.getValue(JRXmlConstants.ATTRIBUTE_uuid);
		if (uuid != null)
		{
			element.setUUID(UUID.fromString(uuid));
		}

		String x = atts.getValue(JRXmlConstants.ATTRIBUTE_x);
		if (x != null && x.length() > 0)
		{
			element.setX(Integer.parseInt(x));
		}

		String y = atts.getValue(JRXmlConstants.ATTRIBUTE_y);
		if (y != null && y.length() > 0)
		{
			element.setY(Integer.parseInt(y));
		}

		String width = atts.getValue(JRXmlConstants.ATTRIBUTE_width);
		if (width != null && width.length() > 0)
		{
			element.setWidth(Integer.parseInt(width));
		}

		String height = atts.getValue(JRXmlConstants.ATTRIBUTE_height);
		if (height != null && height.length() > 0)
		{
			element.setHeight(Integer.parseInt(height));
		}

		String forecolor = atts.getValue(JRXmlConstants.ATTRIBUTE_forecolor);
		if (forecolor != null && forecolor.length() > 0)
		{
			element.setForecolor(JRColorUtil.getColor(forecolor, null));
		}

		String backcolor = atts.getValue(JRXmlConstants.ATTRIBUTE_backcolor);
		if (backcolor != null && backcolor.length() > 0)
		{
			element.setBackcolor(JRColorUtil.getColor(backcolor, null));
		}

		String styleName = atts.getValue(JRXmlConstants.ATTRIBUTE_style);
		if (styleName != null)
		{
			Map<String,JRStyle> stylesMap = jasperPrint.getStylesMap();

			if ( !stylesMap.containsKey(styleName) )
			{
				printXmlLoader.addError(new JRRuntimeException("Unknown report style : " + styleName));
			}

			element.setStyle(stylesMap.get(styleName));
		}

		String origin = atts.getValue(JRXmlConstants.ATTRIBUTE_origin); 
		if (origin != null && origin.length() > 0)
		{
			element.setOrigin(jasperPrint.getOriginsList().get(Integer.parseInt(origin)));
		}
		
		String elementId = atts.getValue(JRXmlConstants.ATTRIBUTE_srcId);
		if (elementId != null && elementId.length() > 0)
		{
			element.setSourceElementId(Integer.parseInt(elementId));
		}

		return element;
	}
	

}
