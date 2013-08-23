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

import org.oss.pdfreporter.engine.JRBoxContainer;
import org.oss.pdfreporter.engine.JRLineBox;
import org.oss.pdfreporter.engine.type.PenEnum;
import org.oss.pdfreporter.engine.util.JRColorUtil;
import org.oss.pdfreporter.engine.util.JRPenUtil;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.xml.parsers.IAttributes;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBoxFactory.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRBoxFactory extends JRBaseFactory
{
	/**
	 *
	 */
	public Object createObject(IAttributes atts)
	{
		JRBoxContainer boxContainer = (JRBoxContainer) digester.peek();
		JRLineBox box = boxContainer.getLineBox();
		setBoxAttributes(atts, box);
		return box;
	}


	public static void setBoxAttributes(IAttributes atts, JRLineBox box)
	{
		PenEnum border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_border));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, box.getPen());
		}

		IColor borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_borderColor), null);
		if (borderColor != null)
		{
			box.getPen().setLineColor(borderColor);
		}

		String padding = atts.getValue(JRXmlConstants.ATTRIBUTE_padding);
		if (padding != null && padding.length() > 0)
		{
			box.setPadding(Integer.parseInt(padding));
		}

		border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_topBorder));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, box.getTopPen());
		}

		borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_topBorderColor), IColor.black);
		if (borderColor != null)
		{
			box.getTopPen().setLineColor(borderColor);
		}

		padding = atts.getValue(JRXmlConstants.ATTRIBUTE_topPadding);
		if (padding != null && padding.length() > 0)
		{
			box.setTopPadding(Integer.parseInt(padding));
		}

		border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_leftBorder));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, box.getLeftPen());
		}

		borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_leftBorderColor), IColor.black);
		if (borderColor != null)
		{
			box.getLeftPen().setLineColor(borderColor);
		}

		padding = atts.getValue(JRXmlConstants.ATTRIBUTE_leftPadding);
		if (padding != null && padding.length() > 0)
		{
			box.setLeftPadding(Integer.parseInt(padding));
		}

		border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_bottomBorder));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, box.getBottomPen());
		}

		borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_bottomBorderColor), IColor.black);
		if (borderColor != null)
		{
			box.getBottomPen().setLineColor(borderColor);
		}

		padding = atts.getValue(JRXmlConstants.ATTRIBUTE_bottomPadding);
		if (padding != null && padding.length() > 0)
		{
			box.setBottomPadding(Integer.parseInt(padding));
		}

		border = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_rightBorder));
		if (border != null)
		{
			JRPenUtil.setLinePenFromPen(border, box.getRightPen());
		}

		borderColor = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_rightBorderColor), IColor.black);
		if (borderColor != null)
		{
			box.getRightPen().setLineColor(borderColor);
		}

		padding = atts.getValue(JRXmlConstants.ATTRIBUTE_rightPadding);
		if (padding != null && padding.length() > 0)
		{
			box.setRightPadding(Integer.parseInt(padding));
		}
	}
	

}
