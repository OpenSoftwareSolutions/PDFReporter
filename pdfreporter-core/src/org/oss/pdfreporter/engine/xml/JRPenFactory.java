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

import org.oss.pdfreporter.converters.DecimalConverter;
import org.oss.pdfreporter.engine.JRCommonGraphicElement;
import org.oss.pdfreporter.engine.JRLineBox;
import org.oss.pdfreporter.engine.JRPen;
import org.oss.pdfreporter.engine.JRStyle;
import org.oss.pdfreporter.engine.type.LineStyleEnum;
import org.oss.pdfreporter.engine.util.JRColorUtil;
import org.oss.pdfreporter.xml.parsers.IAttributes;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPenFactory.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class JRPenFactory extends JRBaseFactory
{

	/**
	 *
	 */
	public Object createObject(IAttributes atts)
	{
		JRCommonGraphicElement graphicElement = (JRCommonGraphicElement) digester.peek();
		setPenAttributes(atts, graphicElement.getLinePen());
		return graphicElement;
	}


	protected static void setPenAttributes(IAttributes atts, JRPen pen)
	{
		String lineWidth = atts.getValue(JRXmlConstants.ATTRIBUTE_lineWidth);
		if (lineWidth != null && lineWidth.length() > 0)
		{
			pen.setLineWidth(DecimalConverter.toDouble(lineWidth).floatValue());
		}

		LineStyleEnum lineStyle = LineStyleEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_lineStyle));
		if (lineStyle != null)
		{
			pen.setLineStyle(lineStyle);
		}

		String lineColor = atts.getValue(JRXmlConstants.ATTRIBUTE_lineColor);
		if (lineColor != null && lineColor.length() > 0)
		{
			pen.setLineColor(JRColorUtil.getColor(lineColor, null));
		}
	}
	

	/**
	 * 
	 */
	public static class Style extends JRPenFactory
	{
		public Object createObject(IAttributes atts)
		{
			JRStyle style = (JRStyle) digester.peek();
			setPenAttributes(atts, style.getLinePen());
			return style;
		}
	}
	
	/**
	 * 
	 */
	public static class Box extends JRPenFactory
	{
		public Object createObject(IAttributes atts)
		{
			JRLineBox box = (JRLineBox) digester.peek();
			setPenAttributes(atts, box.getPen());
			return box;
		}
	}
	
	/**
	 * 
	 */
	public static class Top extends JRPenFactory
	{
		public Object createObject(IAttributes atts)
		{
			JRLineBox box = (JRLineBox) digester.peek();
			setPenAttributes(atts, box.getTopPen());
			return box;
		}
	}
	
	/**
	 * 
	 */
	public static class Left extends JRPenFactory
	{
		public Object createObject(IAttributes atts)
		{
			JRLineBox box = (JRLineBox) digester.peek();
			setPenAttributes(atts, box.getLeftPen());
			return box;
		}
	}
	
	/**
	 * 
	 */
	public static class Bottom extends JRPenFactory
	{
		public Object createObject(IAttributes atts)
		{
			JRLineBox box = (JRLineBox) digester.peek();
			setPenAttributes(atts, box.getBottomPen());
			return box;
		}
	}
	
	/**
	 * 
	 */
	public static class Right extends JRPenFactory
	{
		public Object createObject(IAttributes atts)
		{
			JRLineBox box = (JRLineBox) digester.peek();
			setPenAttributes(atts, box.getRightPen());
			return box;
		}
	}
}
